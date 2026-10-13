package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineBankingApplyRequestAssemblerTest {

    @Test
    void shouldMapToFoqapi00SectionForDifferentFeature() {
        OnlineBankingApplyFrontendRequest request = new OnlineBankingApplyFrontendRequest(
                "B200", "UK003", "003", "0003", "D", "I", "E003", "OP003", "T003", "20261013",
                "A123", "F1", "BIZ1", "OUT", "FX001", "N001", "SB01", "DOC01", "Q1",
                "19900101", "20200101", "20300101", "TW", "USD",
                new BigDecimal("10"), new BigDecimal("320"), new BigDecimal("9.8"),
                "CONT9", "BUY", "TR01", "AT01", new BigDecimal("31.2"), "INVEST"
        );

        OnlineBankingApplyRequestAssembler assembler = new OnlineBankingApplyRequestAssembler(new OuterRequestCommonFieldApplier());
        AddTransactionOuterRequest result = assembler.assemble(request);

        assertEquals("BEGIN8", result.getBegin8());
        assertEquals("FOQAPI00", result.getPgmName8());
        assertEquals("A123", result.getFoqapi00().getFoqapi00ApplicantId());
        assertEquals("USD", result.getFoqapi00().getFoqapi00SpotFxCurency());
        assertEquals(new BigDecimal("320"), result.getFoqapi00().getFoqapi00SpotNtAmount());
    }
}
