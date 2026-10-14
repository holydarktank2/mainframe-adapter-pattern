package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.SpotRemittanceFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpotRemittanceControllerTest {

    @Test
    void submitShouldReturnFacadeResult() {
        SpotRemittanceFacadeService facadeService = new SpotRemittanceFacadeService(null, null, null) {
            @Override
            public SpotRemittanceFrontendResponse process(SpotRemittanceFrontendRequest frontendRequest) {
                return new SpotRemittanceFrontendResponse(true, "0000", "", "TXN-SPOT", "SER-SPOT");
            }
        };

        SpotRemittanceController controller = new SpotRemittanceController(facadeService);

        ResponseEntity<SpotRemittanceFrontendResponse> response = controller.submit(new SpotRemittanceFrontendRequest(
                "S100", "UK002", "002", "0002", "C", "A", "E002", "OP002", "T002", "20261013",
                "02", "D2", "REF002", "TX02", "003", "004", "JPY", "CUST101",
                new BigDecimal("90"), new BigDecimal("95"), "CUST201",
                new BigDecimal("70"), new BigDecimal("72"), "Y"
        ));

        assertEquals(200, response.getStatusCode().value());
        assertEquals("TXN-SPOT", response.getBody().txnNo());
    }
}
