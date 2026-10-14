package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.AbstractTransactionConverter;
import io.github.holydarktank2.mainframe.core.ConversionException;
import io.github.holydarktank2.mainframe.domain.ForeignExchangeRequest;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;

import java.math.BigDecimal;

public class ForeignExchangeConverter extends AbstractTransactionConverter<ForeignExchangeRequest> {

    @Override
    public AddTransactionOuterRequest convert(ForeignExchangeRequest request) {
        validate(request);
        return buildOuterRequest("FOREIGN_EXCHANGE", request);
    }

    private void validate(ForeignExchangeRequest request) {
        String fromCurrency = request.getFromCurrency();
        String toCurrency = request.getToCurrency();
        if (fromCurrency != null && fromCurrency.equalsIgnoreCase(toCurrency)) {
            throw new ConversionException("fromCurrency and toCurrency cannot be the same");
        }

        BigDecimal amount = request.getAmount();
        if (amount != null && amount.signum() <= 0) {
            throw new ConversionException("Foreign exchange amount must be greater than zero");
        }
    }
}
