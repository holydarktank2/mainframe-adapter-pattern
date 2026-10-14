package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForwardRemittanceRequestAssemblerTest {

    @Test
    void shouldMapFrontendRequestToSampleOuterRequest() {
        ForwardRemittanceFrontendRequest request = new ForwardRemittanceFrontendRequest(
                "A100", "UK001", "001", "0001", "B", "M", "E001", "OP001", "T001", "20261013",
                "01", "D1", "REF001", "TX01", "001", "002", "USD", "CUST001",
                new BigDecimal("100.50"), new BigDecimal("102.00"), "CUST002",
                new BigDecimal("80.10"), new BigDecimal("81.00")
        );

        ForwardRemittanceRequestAssembler assembler = new ForwardRemittanceRequestAssembler(
                new OuterRequestCommonFieldApplier(),
                new FsfrmtioCommonFieldApplier()
        );
        AddTransactionOuterRequest result = assembler.assemble(request);

        assertEquals("A100", result.getTxnCode());
        assertEquals("BEGIN13", result.getBegin13());
        assertEquals("FSFRMTIO", result.getPgmName13());
        assertEquals("REF001", result.getFsfrmtio().getFsfrmtioRefNo());
        assertEquals("USD", result.getFsfrmtio().getFsfrmtioCurency());
        assertEquals(new BigDecimal("100.50"), result.getFsfrmtio().getFsfrmtioNewInterbnchNet());
    }
}
