package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpotRemittanceRequestAssemblerTest {

    @Test
    void shouldReuseFsfrmtioCommonFieldsAndSetSpotSpecificField() {
        SpotRemittanceFrontendRequest request = new SpotRemittanceFrontendRequest(
                "S100", "UK002", "002", "0002", "C", "A", "E002", "OP002", "T002", "20261013",
                "02", "D2", "REF002", "TX02", "003", "004", "JPY", "CUST101",
                new BigDecimal("90"), new BigDecimal("95"), "CUST201",
                new BigDecimal("70"), new BigDecimal("72"), "Y"
        );

        SpotRemittanceRequestAssembler assembler = new SpotRemittanceRequestAssembler(
                new OuterRequestCommonFieldApplier(),
                new FsfrmtioCommonFieldApplier()
        );
        AddTransactionOuterRequest result = assembler.assemble(request);

        assertEquals("S100", result.getTxnCode());
        assertEquals("REF002", result.getFsfrmtio().getFsfrmtioRefNo());
        assertEquals("JPY", result.getFsfrmtio().getFsfrmtioCurency());
        assertEquals("Y", result.getFsfrmtio().getFsfrmtioEcMk());
    }
}
