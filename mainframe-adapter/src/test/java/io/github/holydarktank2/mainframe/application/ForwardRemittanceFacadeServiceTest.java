package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.ForwardRemittanceRequestAssembler;
import io.github.holydarktank2.mainframe.integration.ForwardRemittanceResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ForwardRemittanceFacadeServiceTest {

    @Test
    void shouldProcessFrontendRequestToFrontendResponse() {
        BankCenterTransactionClient client = request -> {
            AddTransactionOuterResponse response = new AddTransactionOuterResponse();
            response.setOutReturnCode("0000");
            response.setOutErrorMsg("");
            response.setOutTxnNo("TXN9001");
            response.setOutSerialNo("SER001");
            response.setOutNoticeNo("NTC01");
            response.setOutCustomerNo("CUST998");
            response.setOutFwdContNo1("CONT001");
            response.setOutFwdValueFx1(new BigDecimal("25"));
            response.setOutFwdValueNt1(new BigDecimal("800"));
            response.setOutFwdValueUs1(new BigDecimal("24.8"));
            return response;
        };

        ForwardRemittanceFacadeService service = new ForwardRemittanceFacadeService(
                new ForwardRemittanceRequestAssembler(),
                new ForwardRemittanceResponseMapper(),
                client
        );

        ForwardRemittanceFrontendRequest request = new ForwardRemittanceFrontendRequest(
                "A100", "UK001", "001", "0001", "B", "M", "E001", "OP001", "T001", "20261013",
                "01", "D1", "REF001", "TX01", "001", "002", "USD", "CUST001",
                new BigDecimal("100.50"), new BigDecimal("102.00"), "CUST002",
                new BigDecimal("80.10"), new BigDecimal("81.00")
        );

        ForwardRemittanceFrontendResponse result = service.process(request);

        assertTrue(result.success());
        assertEquals("TXN9001", result.txnNo());
        assertEquals("CONT001", result.contractNo1());
    }
}
