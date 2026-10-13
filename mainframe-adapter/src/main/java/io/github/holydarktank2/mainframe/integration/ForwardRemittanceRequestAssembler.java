package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionFsfrmtioOuterRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.springframework.stereotype.Component;

@Component
public class ForwardRemittanceRequestAssembler {

    public AddTransactionOuterRequest assemble(ForwardRemittanceFrontendRequest frontendRequest) {
        AddTransactionOuterRequest outerRequest = new AddTransactionOuterRequest();

        outerRequest.setTxnCode(frontendRequest.txnCode());
        outerRequest.setTxnUniqueKey(frontendRequest.txnUniqueKey());
        outerRequest.setProcessBrnNo(frontendRequest.processBrnNo());
        outerRequest.setProcessBrnId(frontendRequest.processBrnId());
        outerRequest.setProcessBrnType(frontendRequest.processBrnType());
        outerRequest.setProcessMod(frontendRequest.processMod());
        outerRequest.setEmpNo(frontendRequest.empNo());
        outerRequest.setOpId(frontendRequest.opId());
        outerRequest.setTermId(frontendRequest.termId());
        outerRequest.setBusinessDate(frontendRequest.businessDate());

        outerRequest.setBegin13("BEGIN13");
        outerRequest.setPgmName13("FSFRMTIO");
        outerRequest.setEnd13("END13");

        AddTransactionFsfrmtioOuterRequest fsfrmtio = outerRequest.getFsfrmtio();
        fsfrmtio.setFsfrmtioProcessMod(frontendRequest.processMod());
        fsfrmtio.setFsfrmtioBusType(frontendRequest.busType());
        fsfrmtio.setFsfrmtioDepType(frontendRequest.depType());
        fsfrmtio.setFsfrmtioRefNo(frontendRequest.refNo());
        fsfrmtio.setFsfrmtioTxCod(frontendRequest.txCod());
        fsfrmtio.setFsfrmtioProcessBranch(frontendRequest.processBranch());
        fsfrmtio.setFsfrmtioInwardBranch(frontendRequest.inwardBranch());
        fsfrmtio.setFsfrmtioCurency(frontendRequest.currency());
        fsfrmtio.setFsfrmtioEmpNo(frontendRequest.empNo());
        fsfrmtio.setFsfrmtioNewCustomerId(frontendRequest.newCustomerId());
        fsfrmtio.setFsfrmtioNewInterbnchNet(frontendRequest.newInterbnchNet());
        fsfrmtio.setFsfrmtioNewInterbnchRemit(frontendRequest.newInterbnchRemit());
        fsfrmtio.setFsfrmtioOldCustomerId(frontendRequest.oldCustomerId());
        fsfrmtio.setFsfrmtioOldInterbnchNet(frontendRequest.oldInterbnchNet());
        fsfrmtio.setFsfrmtioOldInterbnchRemit(frontendRequest.oldInterbnchRemit());

        return outerRequest;
    }
}
