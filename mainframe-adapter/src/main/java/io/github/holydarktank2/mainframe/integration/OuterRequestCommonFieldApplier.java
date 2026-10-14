package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.springframework.stereotype.Component;

@Component
public class OuterRequestCommonFieldApplier {

    public void apply(AddTransactionOuterRequest outerRequest,
                      String txnCode,
                      String txnUniqueKey,
                      String processBrnNo,
                      String processBrnId,
                      String processBrnType,
                      String processMod,
                      String empNo,
                      String opId,
                      String termId,
                      String businessDate) {
        outerRequest.setTxnCode(txnCode);
        outerRequest.setTxnUniqueKey(txnUniqueKey);
        outerRequest.setProcessBrnNo(processBrnNo);
        outerRequest.setProcessBrnId(processBrnId);
        outerRequest.setProcessBrnType(processBrnType);
        outerRequest.setProcessMod(processMod);
        outerRequest.setEmpNo(empNo);
        outerRequest.setOpId(opId);
        outerRequest.setTermId(termId);
        outerRequest.setBusinessDate(businessDate);
    }
}
