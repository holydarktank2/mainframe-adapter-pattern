package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Component;

@Component
public class OnlineBankingApplyResponseMapper {
    public OnlineBankingApplyFrontendResponse map(AddTransactionOuterResponse outerResponse) {
        return new OnlineBankingApplyFrontendResponse(
                "0000".equals(outerResponse.getOutReturnCode()),
                outerResponse.getOutReturnCode(),
                outerResponse.getOutErrorMsg(),
                outerResponse.getOutTxnNo(),
                outerResponse.getOutBackRcode(),
                outerResponse.getOutBackLogRrn(),
                outerResponse.getOutSerialNo()
        );
    }
}
