package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.OnlineBankingQueryFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineBankingQueryControllerTest {

    @Test
    void submitShouldReturnFacadeResult() {
        OnlineBankingQueryFacadeService facadeService = new OnlineBankingQueryFacadeService(null, null, null, null, null) {
            @Override
            public OnlineBankingQueryFrontendResponse process(OnlineBankingQueryFrontendRequest frontendRequest) {
                return new OnlineBankingQueryFrontendResponse(true, "0000", "", "TXN-QRY", "B000", "L1000", "SER-QRY");
            }
        };

        OnlineBankingQueryController controller = new OnlineBankingQueryController(facadeService);

        ResponseEntity<OnlineBankingQueryFrontendResponse> response = controller.submit(new OnlineBankingQueryFrontendRequest(
                "B210", "UK004", "004", "0004", "D", "Q", "E004", "OP004", "T004", "20261013",
                "A128", "F2", "BIZ2", "IN", "FX002", "N002", "SB02", "DOC02", "Q2",
                "19910101", "20210101", "20310101", "JP", "JPY",
                new BigDecimal("11"), new BigDecimal("321"), new BigDecimal("9.9"),
                "CONT10", "SELL", "TR02", "AT02", new BigDecimal("30.1"), "TRADE"
        ));

        assertEquals(200, response.getStatusCode().value());
        assertEquals("TXN-QRY", response.getBody().txnNo());
    }
}
