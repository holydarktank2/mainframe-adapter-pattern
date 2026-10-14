package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.application.context.BankMessageData;
import io.github.holydarktank2.mainframe.application.context.CustomerReferenceData;
import io.github.holydarktank2.mainframe.application.context.EbankingContextEnrichmentException;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingContextResolver;
import io.github.holydarktank2.mainframe.application.context.OtherSystemData;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.Foqapi00CommonFieldApplier;
import io.github.holydarktank2.mainframe.integration.OnlineBankingApplyScenarioStrategy;
import io.github.holydarktank2.mainframe.integration.OnlineBankingRequestAssembler;
import io.github.holydarktank2.mainframe.integration.OnlineBankingResponseMapper;
import io.github.holydarktank2.mainframe.integration.OuterRequestCommonFieldApplier;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OnlineBankingApplyFacadeServiceTest {

    @Test
    void shouldProcessApplyFlow() {
        OnlineBankingContextResolver resolver = new OnlineBankingContextResolver(
                (applicantId, processBrnNo) -> new CustomerReferenceData("822", "TGIC-1", "901"),
                fxNo -> new BankMessageData("MSG-1", "00", "N"),
                (businessCod, tradeCode) -> new OtherSystemData("Y", "EXT-FUND")
        );

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
                resolver,
                new OnlineBankingRequestAssembler(new OuterRequestCommonFieldApplier(), new Foqapi00CommonFieldApplier()),
                new OnlineBankingApplyScenarioStrategy(),
                new OnlineBankingResponseMapper(),
                client
        );

        OnlineBankingApplyFrontendResponse result = service.process(sampleRequest());

        assertEquals(false, result.success());
        assertEquals("TXN-EBK", result.txnNo());
        assertEquals("B001", result.backReturnCode());
    }

    @Test
    void shouldFailFastWhenContextResolverFails() {
        OnlineBankingContextResolver resolver = new OnlineBankingContextResolver(
                (applicantId, processBrnNo) -> { throw new IllegalStateException("db fail"); },
                fxNo -> new BankMessageData("MSG-1", "00", "N"),
                (businessCod, tradeCode) -> new OtherSystemData("Y", "EXT")
        );

        OnlineBankingApplyFacadeService service = new OnlineBankingApplyFacadeService(
                resolver,
                new OnlineBankingRequestAssembler(new OuterRequestCommonFieldApplier(), new Foqapi00CommonFieldApplier()),
                new OnlineBankingApplyScenarioStrategy(),
                new OnlineBankingResponseMapper(),
                request -> new AddTransactionOuterResponse()
        );

        assertThrows(EbankingContextEnrichmentException.class, () -> service.process(sampleRequest()));
    }

    private OnlineBankingApplyFrontendRequest sampleRequest() {
        return new OnlineBankingApplyFrontendRequest(
                "B200", "UK003", "003", "0003", "D", "I", "E003", "OP003", "T003", "20261013",
                "A123", "F1", "BIZ1", "OUT", "FX001", "N001", "SB01", "DOC01", "Q1",
                "19900101", "20200101", "20300101", "TW", "USD",
                new BigDecimal("10"), new BigDecimal("320"), new BigDecimal("9.8"),
                "CONT9", "BUY", "TR01", "AT01", new BigDecimal("31.2"), "INVEST"
        );
    }
}
