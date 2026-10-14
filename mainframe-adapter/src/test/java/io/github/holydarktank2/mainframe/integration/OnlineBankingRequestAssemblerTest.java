package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.application.context.BankMessageData;
import io.github.holydarktank2.mainframe.application.context.CustomerReferenceData;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.application.context.OtherSystemData;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineBankingRequestAssemblerTest {

    @Test
    void shouldApplyCommonAndScenarioFields() {
        OnlineBankingApplyFrontendRequest request = sampleApplyRequest();
        OnlineBankingEnrichedContext context = new OnlineBankingEnrichedContext(
                request,
                new CustomerReferenceData("822", "TGIC-A123", "901"),
                new BankMessageData("MSG-01", "00", "N"),
                new OtherSystemData("Y", "EXT-FUND")
        );

        OnlineBankingRequestAssembler assembler = new OnlineBankingRequestAssembler(
                new OuterRequestCommonFieldApplier(),
                new Foqapi00CommonFieldApplier()
        );

        AddTransactionOuterRequest result = assembler.assemble(context, new OnlineBankingApplyScenarioStrategy());

        assertEquals("B200", result.getTxnCode());
        assertEquals("FOQAPI00", result.getPgmName8());
        assertEquals("APPLY-F1", result.getFoqapi00().getFoqapi00FuncCod());
        assertEquals("822", result.getFoqapi00().getFoqapi00BankCode());
        assertEquals("TGIC-A123", result.getFoqapi00().getFoqapi00Noc1ToTgic());
        assertEquals("MSG-01", result.getFoqapi00().getFoqapi00SerialNo());
        assertEquals("EXT-FUND", result.getFoqapi00().getFoqapi00UseOfFunds());
    }

    @Test
    void queryStrategyShouldSetDifferentScenarioFields() {
        OnlineBankingApplyFrontendRequest request = sampleApplyRequest();
        OnlineBankingEnrichedContext context = new OnlineBankingEnrichedContext(
                request,
                new CustomerReferenceData("822", "TGIC-A123", "901"),
                new BankMessageData("MSG-01", "00", "N"),
                new OtherSystemData("Y", "EXT-FUND")
        );

        OnlineBankingRequestAssembler assembler = new OnlineBankingRequestAssembler(
                new OuterRequestCommonFieldApplier(),
                new Foqapi00CommonFieldApplier()
        );

        AddTransactionOuterRequest result = assembler.assemble(context, new OnlineBankingQueryScenarioStrategy());

        assertEquals("QUERY-F1", result.getFoqapi00().getFoqapi00FuncCod());
        assertEquals("QRY-BIZ1", result.getFoqapi00().getFoqapi00BusinessCod());
    }

    private OnlineBankingApplyFrontendRequest sampleApplyRequest() {
        return new OnlineBankingApplyFrontendRequest(
                "B200", "UK003", "003", "0003", "D", "I", "E003", "OP003", "T003", "20261013",
                "A123", "F1", "BIZ1", "OUT", "FX001", "N001", "SB01", "DOC01", "Q1",
                "19900101", "20200101", "20300101", "TW", "USD",
                new BigDecimal("10"), new BigDecimal("320"), new BigDecimal("9.8"),
                "CONT9", "BUY", "TR01", "AT01", new BigDecimal("31.2"), "INVEST"
        );
    }
}
