package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.AbstractTransactionConverter;
import io.github.holydarktank2.mainframe.core.ConversionException;
import io.github.holydarktank2.mainframe.domain.SpotRemittanceRequest;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;

import java.math.BigDecimal;

public class SpotRemittanceConverter extends AbstractTransactionConverter<SpotRemittanceRequest> {
    @Override
    public AddTransactionOuterRequest convert(SpotRemittanceRequest request) {
        validate(request);
        return buildOuterRequest("SPOT_REMITTANCE", request);
    }

    private void validate(SpotRemittanceRequest request) {
        BigDecimal amount = request.getAmount();
        if (amount != null && amount.signum() <= 0) {
            throw new ConversionException("Spot remittance amount must be greater than zero");
        }
        if (request.getCurrency() != null && request.getCurrency().length() != 3) {
            throw new ConversionException("Currency must be a 3-letter code");
        }
    }
}
