package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.springframework.stereotype.Component;

@Component
public class ForwardRemittanceRequestAssembler {
    private final OuterRequestCommonFieldApplier outerRequestCommonFieldApplier;
    private final FsfrmtioCommonFieldApplier fsfrmtioCommonFieldApplier;

    public ForwardRemittanceRequestAssembler(OuterRequestCommonFieldApplier outerRequestCommonFieldApplier,
                                             FsfrmtioCommonFieldApplier fsfrmtioCommonFieldApplier) {
        this.outerRequestCommonFieldApplier = outerRequestCommonFieldApplier;
        this.fsfrmtioCommonFieldApplier = fsfrmtioCommonFieldApplier;
    }

    public AddTransactionOuterRequest assemble(ForwardRemittanceFrontendRequest frontendRequest) {
        AddTransactionOuterRequest outerRequest = new AddTransactionOuterRequest();

        outerRequestCommonFieldApplier.apply(
                outerRequest,
                frontendRequest.txnCode(),
                frontendRequest.txnUniqueKey(),
                frontendRequest.processBrnNo(),
                frontendRequest.processBrnId(),
                frontendRequest.processBrnType(),
                frontendRequest.processMod(),
                frontendRequest.empNo(),
                frontendRequest.opId(),
                frontendRequest.termId(),
                frontendRequest.businessDate()
        );

        outerRequest.setBegin13("BEGIN13");
        outerRequest.setPgmName13("FSFRMTIO");
        outerRequest.setEnd13("END13");

        fsfrmtioCommonFieldApplier.apply(
                outerRequest.getFsfrmtio(),
                frontendRequest.processMod(),
                frontendRequest.busType(),
                frontendRequest.depType(),
                frontendRequest.refNo(),
                frontendRequest.txCod(),
                frontendRequest.processBranch(),
                frontendRequest.inwardBranch(),
                frontendRequest.currency(),
                frontendRequest.empNo(),
                frontendRequest.newCustomerId(),
                frontendRequest.newInterbnchNet(),
                frontendRequest.newInterbnchRemit(),
                frontendRequest.oldCustomerId(),
                frontendRequest.oldInterbnchNet(),
                frontendRequest.oldInterbnchRemit()
        );

        return outerRequest;
    }
}
