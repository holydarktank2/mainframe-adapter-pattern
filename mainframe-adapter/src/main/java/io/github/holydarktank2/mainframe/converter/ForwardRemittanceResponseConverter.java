package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.TransactionResponseConverter;
import io.github.holydarktank2.mainframe.domain.response.ForwardRemittanceResponse;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterResponse;

public class ForwardRemittanceResponseConverter implements TransactionResponseConverter<ForwardRemittanceResponse> {
    @Override
    public ForwardRemittanceResponse convert(AddTransactionOuterResponse response) {
        return new ForwardRemittanceResponse(
                response.getString("basic", "refNo"),
                response.getString("remittance", "bankSerialNo"),
                response.getResponseCode(),
                response.getResponseMessage()
        );
    }
}
