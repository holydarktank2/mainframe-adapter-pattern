package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.TransactionResponseConverter;
import io.github.holydarktank2.mainframe.domain.response.ForeignExchangeResponse;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterResponse;

public class ForeignExchangeResponseConverter implements TransactionResponseConverter<ForeignExchangeResponse> {
    @Override
    public ForeignExchangeResponse convert(AddTransactionOuterResponse response) {
        return new ForeignExchangeResponse(
                response.getString("basic", "refNo"),
                response.getString("forex", "dealNo"),
                response.getResponseCode(),
                response.getResponseMessage()
        );
    }
}
