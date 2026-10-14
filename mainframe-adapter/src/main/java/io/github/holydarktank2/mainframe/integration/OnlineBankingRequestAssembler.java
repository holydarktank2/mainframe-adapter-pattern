package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.springframework.stereotype.Component;

@Component
public class OnlineBankingRequestAssembler {
    private final OuterRequestCommonFieldApplier outerRequestCommonFieldApplier;
    private final Foqapi00CommonFieldApplier foqapi00CommonFieldApplier;

    public OnlineBankingRequestAssembler(OuterRequestCommonFieldApplier outerRequestCommonFieldApplier,
                                         Foqapi00CommonFieldApplier foqapi00CommonFieldApplier) {
        this.outerRequestCommonFieldApplier = outerRequestCommonFieldApplier;
        this.foqapi00CommonFieldApplier = foqapi00CommonFieldApplier;
    }

    public AddTransactionOuterRequest assemble(OnlineBankingEnrichedContext context,
                                               OnlineBankingScenarioStrategy scenarioStrategy) {
        AddTransactionOuterRequest outerRequest = new AddTransactionOuterRequest();
        var request = context.frontendRequest();

        outerRequestCommonFieldApplier.apply(
                outerRequest,
                request.txnCode(),
                request.txnUniqueKey(),
                request.processBrnNo(),
                request.processBrnId(),
                request.processBrnType(),
                request.processMod(),
                request.empNo(),
                request.opId(),
                request.termId(),
                request.businessDate()
        );

        foqapi00CommonFieldApplier.apply(outerRequest.getFoqapi00(), context);
        scenarioStrategy.applyScenarioFields(outerRequest, outerRequest.getFoqapi00(), context);
        return outerRequest;
    }
}
