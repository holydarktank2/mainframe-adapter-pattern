package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.AbstractTransactionConverter;
import io.github.holydarktank2.mainframe.core.ConversionException;
import io.github.holydarktank2.mainframe.domain.ForwardRemittanceRequest;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;

import java.math.BigDecimal;

public class ForwardRemittanceConverter extends AbstractTransactionConverter<ForwardRemittanceRequest> {

    @Override
    public AddTransactionOuterRequest convert(ForwardRemittanceRequest request) {
        validate(request);
        return buildOuterRequest("FORWARD_REMITTANCE", request);
    }

    private void validate(ForwardRemittanceRequest request) {
        if (!"FWD".equalsIgnoreCase(request.getDealType())) {
            throw new ConversionException("Forward remittance dealType must be FWD");
        }
        BigDecimal amount = request.getAmount();
        if (amount != null && amount.signum() <= 0) {
            throw new ConversionException("Forward remittance amount must be greater than zero");
        }
    }
}
