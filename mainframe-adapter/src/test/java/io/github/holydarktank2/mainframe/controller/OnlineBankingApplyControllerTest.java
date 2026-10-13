package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.OnlineBankingApplyFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineBankingApplyControllerTest {

    @Test
    void submitShouldReturnFacadeResult() {
        OnlineBankingApplyFacadeService facadeService = new OnlineBankingApplyFacadeService(null, null, null) {
            @Override
            public OnlineBankingApplyFrontendResponse process(OnlineBankingApplyFrontendRequest frontendRequest) {
                return new OnlineBankingApplyFrontendResponse(false, "1001", "INVALID", "TXN-EBK", "B001", "L0009", "SER-EBK");
            }
        };

        OnlineBankingApplyController controller = new OnlineBankingApplyController(facadeService);

        ResponseEntity<OnlineBankingApplyFrontendResponse> response = controller.submit(new OnlineBankingApplyFrontendRequest(
                "B200", "UK003", "003", "0003", "D", "I", "E003", "OP003", "T003", "20261013",
                "A123", "F1", "BIZ1", "OUT", "FX001", "N001", "SB01", "DOC01", "Q1",
                "19900101", "20200101", "20300101", "TW", "USD",
                new BigDecimal("10"), new BigDecimal("320"), new BigDecimal("9.8"),
                "CONT9", "BUY", "TR01", "AT01", new BigDecimal("31.2"), "INVEST"
        ));

        assertEquals(200, response.getStatusCode().value());
        assertEquals("TXN-EBK", response.getBody().txnNo());
    }
}
