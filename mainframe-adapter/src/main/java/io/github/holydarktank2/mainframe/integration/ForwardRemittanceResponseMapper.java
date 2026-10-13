package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendResponse;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Component;

@Component
public class ForwardRemittanceResponseMapper {

    public ForwardRemittanceFrontendResponse map(AddTransactionOuterResponse outerResponse) {
        String returnCode = outerResponse.getOutReturnCode();
        return new ForwardRemittanceFrontendResponse(
                "0000".equals(returnCode),
                returnCode,
                outerResponse.getOutErrorMsg(),
                outerResponse.getOutTxnNo(),
                outerResponse.getOutSerialNo(),
                outerResponse.getOutNoticeNo(),
                outerResponse.getOutCustomerNo(),
                outerResponse.getOutFwdContNo1(),
                outerResponse.getOutFwdValueFx1(),
                outerResponse.getOutFwdValueNt1(),
                outerResponse.getOutFwdValueUs1()
        );
    }
}
