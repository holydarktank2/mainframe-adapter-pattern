package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.application.context.BankMessageData;
import io.github.holydarktank2.mainframe.application.context.CustomerReferenceData;
import io.github.holydarktank2.mainframe.application.context.EbankingContextEnrichmentException;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingContextResolver;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.application.context.OtherSystemData;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.integration.ebanking.BankMessageProvider;
import io.github.holydarktank2.mainframe.integration.ebanking.CustomerReferenceProvider;
import io.github.holydarktank2.mainframe.integration.ebanking.OtherSystemProvider;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OnlineBankingContextResolverTest {

    @Test
    void shouldMergeThreeSourceData() {
        OnlineBankingContextResolver resolver = new OnlineBankingContextResolver(
                (applicantId, processBrnNo) -> new CustomerReferenceData("822", "TGIC-1", "901"),
                fxNo -> new BankMessageData("MSG-1", "00", "N"),
                (businessCod, tradeCode) -> new OtherSystemData("Y", "EXT-FUND")
        );

        OnlineBankingEnrichedContext context = resolver.resolve(sampleRequest());

        assertEquals("822", context.customerReferenceData().bankCode());
        assertEquals("MSG-1", context.bankMessageData().serialNo());
        assertEquals("EXT-FUND", context.otherSystemData().useOfFundsOverride());
    }

    @Test
    void shouldThrowWhenProviderFails() {
        CustomerReferenceProvider customerProvider = (applicantId, processBrnNo) -> {
            throw new IllegalStateException("db down");
        };
        BankMessageProvider bankProvider = fxNo -> new BankMessageData("MSG", "00", "N");
        OtherSystemProvider otherProvider = (businessCod, tradeCode) -> new OtherSystemData("Y", "EXT");

        OnlineBankingContextResolver resolver = new OnlineBankingContextResolver(customerProvider, bankProvider, otherProvider);

        assertThrows(EbankingContextEnrichmentException.class, () -> resolver.resolve(sampleRequest()));
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
