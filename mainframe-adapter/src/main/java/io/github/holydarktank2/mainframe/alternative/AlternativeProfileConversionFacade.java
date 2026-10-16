package io.github.holydarktank2.mainframe.alternative;

import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Alternative architecture: Profile Registry + Enrichment Pipeline + Conversion Engine.
 *
 * This is intentionally separated from the existing assembler strategy flow.
 */
public class AlternativeProfileConversionFacade {

    public enum Scenario {
        APPLY,
        QUERY
    }

    public record OnlineBankingCommand(
            String txnCode,
            String txnUniqueKey,
            String processBrnNo,
            String processBrnId,
            String processBrnType,
            String processMod,
            String empNo,
            String opId,
            String termId,
            String businessDate,
            String applicantId,
            String funcCod,
            String businessCod,
            String fxNo,
            String useOfFunds
    ) {
    }

    @FunctionalInterface
    public interface EnrichmentProvider {
        void enrich(OnlineBankingCommand command, Map<String, Object> context);
    }

    public record MappingRule(
            String fieldName,
            boolean required,
            BiFunction<OnlineBankingCommand, Map<String, Object>, Object> extractor,
            BiConsumer<AddTransactionOuterRequest, Object> writer
    ) {
    }

    public record MappingProfile(
            Consumer<AddTransactionOuterRequest> initializer,
            List<MappingRule> rules
    ) {
    }

    private final List<EnrichmentProvider> enrichmentPipeline;
    private final Map<Scenario, MappingProfile> profileRegistry;

    public AlternativeProfileConversionFacade() {
        this.enrichmentPipeline = List.of(
                (cmd, ctx) -> ctx.put("bankCode", "822"),
                (cmd, ctx) -> ctx.put("serialNo", "MSG-" + cmd.fxNo()),
                (cmd, ctx) -> ctx.put("useOfFundsOverride", cmd.useOfFunds())
        );
        this.profileRegistry = defaultProfiles();
    }

    public AddTransactionOuterRequest convert(Scenario scenario, OnlineBankingCommand command) {
        Map<String, Object> context = new HashMap<>();
        for (EnrichmentProvider provider : enrichmentPipeline) {
            provider.enrich(command, context);
        }

        MappingProfile profile = profileRegistry.get(scenario);
        if (profile == null) {
            throw new IllegalArgumentException("Profile not found: " + scenario);
        }

        AddTransactionOuterRequest request = new AddTransactionOuterRequest();
        profile.initializer().accept(request);
        for (MappingRule rule : profile.rules()) {
            Object value = rule.extractor().apply(command, context);
            if (rule.required() && (value == null || (value instanceof String s && s.isBlank()))) {
                throw new IllegalArgumentException("Required field missing: " + rule.fieldName());
            }
            if (value != null) {
                rule.writer().accept(request, value);
            }
        }
        return request;
    }

    private Map<Scenario, MappingProfile> defaultProfiles() {
        Map<Scenario, MappingProfile> profiles = new HashMap<>();

        profiles.put(Scenario.APPLY, new MappingProfile(
                req -> {
                    req.setBegin8("BEGIN8");
                    req.setPgmName8("FOQAPI00");
                    req.setEnd8("END8");
                },
                List.of(
                        new MappingRule("txnCode", true, (c, x) -> c.txnCode(), (r, v) -> r.setTxnCode((String) v)),
                        new MappingRule("txnUniqueKey", true, (c, x) -> c.txnUniqueKey(), (r, v) -> r.setTxnUniqueKey((String) v)),
                        new MappingRule("bankCode", true, (c, x) -> x.get("bankCode"), (r, v) -> r.getFoqapi00().setFoqapi00BankCode((String) v)),
                        new MappingRule("serialNo", true, (c, x) -> x.get("serialNo"), (r, v) -> r.getFoqapi00().setFoqapi00SerialNo((String) v)),
                        new MappingRule("funcCod", true, (c, x) -> "APPLY-" + c.funcCod(), (r, v) -> r.getFoqapi00().setFoqapi00FuncCod((String) v)),
                        new MappingRule("businessCod", true, (c, x) -> c.businessCod(), (r, v) -> r.getFoqapi00().setFoqapi00BusinessCod((String) v)),
                        new MappingRule("useOfFunds", true, (c, x) -> x.get("useOfFundsOverride"), (r, v) -> r.getFoqapi00().setFoqapi00UseOfFunds((String) v))
                )
        ));

        profiles.put(Scenario.QUERY, new MappingProfile(
                req -> {
                    req.setBegin8("BEGIN8");
                    req.setPgmName8("FOQAPI00");
                    req.setEnd8("END8");
                },
                List.of(
                        new MappingRule("txnCode", true, (c, x) -> c.txnCode(), (r, v) -> r.setTxnCode((String) v)),
                        new MappingRule("txnUniqueKey", true, (c, x) -> c.txnUniqueKey(), (r, v) -> r.setTxnUniqueKey((String) v)),
                        new MappingRule("bankCode", true, (c, x) -> x.get("bankCode"), (r, v) -> r.getFoqapi00().setFoqapi00BankCode((String) v)),
                        new MappingRule("serialNo", true, (c, x) -> x.get("serialNo"), (r, v) -> r.getFoqapi00().setFoqapi00SerialNo((String) v)),
                        new MappingRule("funcCod", true, (c, x) -> "QUERY-" + c.funcCod(), (r, v) -> r.getFoqapi00().setFoqapi00FuncCod((String) v)),
                        new MappingRule("businessCod", true, (c, x) -> "QRY-" + c.businessCod(), (r, v) -> r.getFoqapi00().setFoqapi00BusinessCod((String) v)),
                        new MappingRule("useOfFunds", true, (c, x) -> x.get("useOfFundsOverride"), (r, v) -> r.getFoqapi00().setFoqapi00UseOfFunds((String) v))
                )
        ));

        return profiles;
    }
}
