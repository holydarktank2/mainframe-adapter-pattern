package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.OnlineBankingApplyRequestAssembler;
import io.github.holydarktank2.mainframe.integration.OnlineBankingApplyResponseMapper;
import io.github.holydarktank2.mainframe.integration.OuterRequestCommonFieldApplier;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineBankingApplyFacadeServiceTest {

    @Test
    void shouldProcessOnlineBankingApplyFlow() {
        BankCenterTransactionClient client = request -> {
            AddTransactionOuterResponse response = new AddTransactionOuterResponse();
            response.setOutReturnCode("1001");
            response.setOutErrorMsg("INVALID DOC");
            response.setOutTxnNo("TXN-EBK");
            response.setOutBackRcode("B001");
            response.setOutBackLogRrn("L0009");
            response.setOutSerialNo("SER-EBK");
            return response;
        };

        OnlineBankingApplyFacadeService service = new OnlineBankingApplyFacadeService(
                new OnlineBankingApplyRequestAssembler(new OuterRequestCommonFieldApplier()),
                new OnlineBankingApplyResponseMapper(),
                client
        );

        OnlineBankingApplyFrontendResponse result = service.process(new OnlineBankingApplyFrontendRequest(
                "B200", "UK003", "003", "0003", "D", "I", "E003", "OP003", "T003", "20261013",
                "A123", "F1", "BIZ1", "OUT", "FX001", "N001", "SB01", "DOC01", "Q1",
                "19900101", "20200101", "20300101", "TW", "USD",
                new BigDecimal("10"), new BigDecimal("320"), new BigDecimal("9.8"),
                "CONT9", "BUY", "TR01", "AT01", new BigDecimal("31.2"), "INVEST"
        ));

        assertEquals(false, result.success());
        assertEquals("TXN-EBK", result.txnNo());
        assertEquals("B001", result.backReturnCode());
    }
}
