package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.application.context.BankMessageData;
import io.github.holydarktank2.mainframe.application.context.CustomerReferenceData;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingContextResolver;
import io.github.holydarktank2.mainframe.application.context.OtherSystemData;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.Foqapi00CommonFieldApplier;
import io.github.holydarktank2.mainframe.integration.OnlineBankingQueryScenarioStrategy;
import io.github.holydarktank2.mainframe.integration.OnlineBankingRequestAssembler;
import io.github.holydarktank2.mainframe.integration.OnlineBankingResponseMapper;
import io.github.holydarktank2.mainframe.integration.OuterRequestCommonFieldApplier;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineBankingQueryFacadeServiceTest {

    @Test
    void shouldProcessQueryFlow() {
        OnlineBankingContextResolver resolver = new OnlineBankingContextResolver(
                (applicantId, processBrnNo) -> new CustomerReferenceData("822", "TGIC-1", "901"),
                fxNo -> new BankMessageData("MSG-1", "00", "N"),
                (businessCod, tradeCode) -> new OtherSystemData("Y", "EXT-FUND")
        );

        BankCenterTransactionClient client = request -> {
            AddTransactionOuterResponse response = new AddTransactionOuterResponse();
            response.setOutReturnCode("0000");
            response.setOutTxnNo("TXN-QRY");
            response.setOutBackRcode("B000");
            response.setOutBackLogRrn("L1000");
            response.setOutSerialNo("SER-QRY");
            return response;
        };

        OnlineBankingQueryFacadeService service = new OnlineBankingQueryFacadeService(
                resolver,
                new OnlineBankingRequestAssembler(new OuterRequestCommonFieldApplier(), new Foqapi00CommonFieldApplier()),
                new OnlineBankingQueryScenarioStrategy(),
                new OnlineBankingResponseMapper(),
                client
        );

        OnlineBankingQueryFrontendResponse result = service.process(sampleQueryRequest());

        assertEquals(true, result.success());
        assertEquals("TXN-QRY", result.txnNo());
    }

    private OnlineBankingQueryFrontendRequest sampleQueryRequest() {
        return new OnlineBankingQueryFrontendRequest(
                "B210", "UK004", "004", "0004", "D", "Q", "E004", "OP004", "T004", "20261013",
                "A128", "F2", "BIZ2", "IN", "FX002", "N002", "SB02", "DOC02", "Q2",
                "19910101", "20210101", "20310101", "JP", "JPY",
                new BigDecimal("11"), new BigDecimal("321"), new BigDecimal("9.9"),
                "CONT10", "SELL", "TR02", "AT02", new BigDecimal("30.1"), "TRADE"
        );
    }
}
