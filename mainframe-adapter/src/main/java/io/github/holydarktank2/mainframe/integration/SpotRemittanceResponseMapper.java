package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendResponse;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Component;

@Component
public class SpotRemittanceResponseMapper {
    public SpotRemittanceFrontendResponse map(AddTransactionOuterResponse outerResponse) {
        return new SpotRemittanceFrontendResponse(
                "0000".equals(outerResponse.getOutReturnCode()),
                outerResponse.getOutReturnCode(),
                outerResponse.getOutErrorMsg(),
                outerResponse.getOutTxnNo(),
                outerResponse.getOutSerialNo()
        );
    }
}
