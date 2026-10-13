package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.AbstractTransactionConverter;
import io.github.holydarktank2.mainframe.core.ConversionException;
import io.github.holydarktank2.mainframe.domain.OnlineBankingRequest;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;

public class OnlineBankingConverter extends AbstractTransactionConverter<OnlineBankingRequest> {

    @Override
    public AddTransactionOuterRequest convert(OnlineBankingRequest request) {
        validate(request);
        return buildOuterRequest("ONLINE_BANKING", request);
    }

    private void validate(OnlineBankingRequest request) {
        String email = request.getEmail();
        if (email != null && !email.contains("@")) {
            throw new ConversionException("Email format is invalid");
        }
    }
}
