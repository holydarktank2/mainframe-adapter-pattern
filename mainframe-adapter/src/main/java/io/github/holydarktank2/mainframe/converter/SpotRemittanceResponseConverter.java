package io.github.holydarktank2.mainframe.converter;

import io.github.holydarktank2.mainframe.core.TransactionResponseConverter;
import io.github.holydarktank2.mainframe.domain.response.SpotRemittanceResponse;
import io.github.holydarktank2.mainframe.outer.AddTransactionOuterResponse;

public class SpotRemittanceResponseConverter implements TransactionResponseConverter<SpotRemittanceResponse> {
    @Override
    public SpotRemittanceResponse convert(AddTransactionOuterResponse response) {
        return new SpotRemittanceResponse(
                response.getString("basic", "refNo"),
                response.getString("remittance", "bankSerialNo"),
                response.getResponseCode(),
                response.getResponseMessage()
        );
    }
}
