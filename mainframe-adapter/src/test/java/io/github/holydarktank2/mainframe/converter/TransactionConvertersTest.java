package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.ConversionException;
import io.github.holydarktank2.mainframe.domain.ForeignExchangeRequest;
import io.github.holydarktank2.mainframe.domain.ForwardRemittanceRequest;
import io.github.holydarktank2.mainframe.domain.OnlineBankingRequest;
import io.github.holydarktank2.mainframe.domain.SpotRemittanceRequest;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionConvertersTest {

    @Test
    void forwardRemittanceShouldConvertMappedFields() {
        ForwardRemittanceRequest request = ForwardRemittanceRequest.builder()
                .refNo("TX001")
                .contractNo("C001")
                .dealType("FWD")
                .amount(new BigDecimal("1000.00"))
                .build();

        AddTransactionOuterRequest outerRequest = new ForwardRemittanceConverter().convert(request);

        assertEquals("FORWARD_REMITTANCE", outerRequest.getTransactionType());
        assertEquals("TX001", outerRequest.getSection("basic").get("refNo"));
        assertEquals("C001", outerRequest.getSection("remittance").get("contractNo"));
    }

    @Test
    void spotRemittanceShouldRejectInvalidCurrency() {
        SpotRemittanceRequest request = SpotRemittanceRequest.builder()
                .refNo("TX002")
                .beneficiaryAccount("00112233")
                .currency("US")
                .amount(new BigDecimal("120"))
                .build();

        assertThrows(ConversionException.class, () -> new SpotRemittanceConverter().convert(request));
    }

    @Test
    void onlineBankingShouldRequireMappedFields() {
        OnlineBankingRequest request = OnlineBankingRequest.builder()
                .refNo("TX003")
                .applicantId("A123456789")
                .build();

        assertThrows(ConversionException.class, () -> new OnlineBankingConverter().convert(request));
    }

    @Test
    void foreignExchangeShouldRejectSameCurrencies() {
        ForeignExchangeRequest request = ForeignExchangeRequest.builder()
                .refNo("TX004")
                .fromCurrency("USD")
                .toCurrency("USD")
                .amount(new BigDecimal("200"))
                .build();

        assertThrows(ConversionException.class, () -> new ForeignExchangeConverter().convert(request));
    }
}
