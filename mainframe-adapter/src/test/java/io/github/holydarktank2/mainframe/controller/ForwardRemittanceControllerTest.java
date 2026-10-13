package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.ForwardRemittanceFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForwardRemittanceControllerTest {

    @Test
    void submitShouldReturnFacadeResult() {
        ForwardRemittanceFacadeService facadeService = new ForwardRemittanceFacadeService(null, null, null) {
            @Override
            public ForwardRemittanceFrontendResponse process(ForwardRemittanceFrontendRequest frontendRequest) {
                return new ForwardRemittanceFrontendResponse(true, "0000", "", "TXN01", "SER01", "N01", "C01", "F01", new BigDecimal("1"), new BigDecimal("2"), new BigDecimal("3"));
            }
        };

        ForwardRemittanceController controller = new ForwardRemittanceController(facadeService);

        ResponseEntity<ForwardRemittanceFrontendResponse> response = controller.submit(new ForwardRemittanceFrontendRequest(
                "A100", "UK001", "001", "0001", "B", "M", "E001", "OP001", "T001", "20261013",
                "01", "D1", "REF001", "TX01", "001", "002", "USD", "CUST001",
                new BigDecimal("100.50"), new BigDecimal("102.00"), "CUST002",
                new BigDecimal("80.10"), new BigDecimal("81.00")
        ));

        assertEquals(200, response.getStatusCode().value());
        assertEquals("TXN01", response.getBody().txnNo());
    }
}
