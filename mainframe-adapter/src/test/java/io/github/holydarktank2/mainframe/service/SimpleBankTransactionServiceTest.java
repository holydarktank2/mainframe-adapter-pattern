package io.github.holydarktank2.mainframe.service;

import io.github.holydarktank2.mainframe.domain.ForwardRemittanceRequest;
import io.github.holydarktank2.mainframe.domain.response.ForwardRemittanceResponse;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterResponse;
import io.github.holydarktank2.mainframe.outer.BankApiClient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleBankTransactionServiceTest {

    @Test
    void executeForwardRemittanceShouldMapRequestAndResponse() {
        SimpleBankTransactionService service = new SimpleBankTransactionService();
        ForwardRemittanceRequest request = ForwardRemittanceRequest.builder()
                .refNo("TX001")
                .contractNo("CTR001")
                .dealType("FWD")
                .amount(new BigDecimal("2000"))
                .build();

        AddTransactionOuterRequest[] capturedRequest = new AddTransactionOuterRequest[1];
        BankApiClient bankApiClient = outerRequest -> {
            capturedRequest[0] = outerRequest;
            AddTransactionOuterResponse response = new AddTransactionOuterResponse();
            response.setTransactionType(outerRequest.getTransactionType());
            response.setResponseCode("0000");
            response.setResponseMessage("SUCCESS");
            response.putSectionField("basic", "refNo", outerRequest.getSection("basic").get("refNo"));
            response.putSectionField("remittance", "bankSerialNo", "BK20261013001");
            return response;
        };

        ForwardRemittanceResponse result = service.executeForwardRemittance(request, bankApiClient);

        assertEquals("FORWARD_REMITTANCE", capturedRequest[0].getTransactionType());
        assertEquals("TX001", capturedRequest[0].getSection("basic").get("refNo"));
        assertEquals("CTR001", capturedRequest[0].getSection("remittance").get("contractNo"));
        assertEquals("0000", result.status());
        assertEquals("BK20261013001", result.bankSerialNo());
    }
}
