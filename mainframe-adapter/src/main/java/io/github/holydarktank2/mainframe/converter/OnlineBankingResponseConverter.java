package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.TransactionResponseConverter;
import io.github.holydarktank2.mainframe.domain.response.OnlineBankingResponse;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterResponse;

public class OnlineBankingResponseConverter implements TransactionResponseConverter<OnlineBankingResponse> {
    @Override
    public OnlineBankingResponse convert(AddTransactionOuterResponse response) {
        return new OnlineBankingResponse(
                response.getString("basic", "refNo"),
                response.getString("onlineBanking", "applicationNo"),
                response.getResponseCode(),
                response.getResponseMessage()
        );
    }
}
