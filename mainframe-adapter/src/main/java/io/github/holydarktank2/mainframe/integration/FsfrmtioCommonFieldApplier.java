package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionFsfrmtioOuterRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FsfrmtioCommonFieldApplier {

    public void apply(AddTransactionFsfrmtioOuterRequest fsfrmtio,
                      String processMod,
                      String busType,
                      String depType,
                      String refNo,
                      String txCod,
                      String processBranch,
                      String inwardBranch,
                      String currency,
                      String empNo,
                      String newCustomerId,
                      BigDecimal newInterbnchNet,
                      BigDecimal newInterbnchRemit,
                      String oldCustomerId,
                      BigDecimal oldInterbnchNet,
                      BigDecimal oldInterbnchRemit) {
        fsfrmtio.setFsfrmtioProcessMod(processMod);
        fsfrmtio.setFsfrmtioBusType(busType);
        fsfrmtio.setFsfrmtioDepType(depType);
        fsfrmtio.setFsfrmtioRefNo(refNo);
        fsfrmtio.setFsfrmtioTxCod(txCod);
        fsfrmtio.setFsfrmtioProcessBranch(processBranch);
        fsfrmtio.setFsfrmtioInwardBranch(inwardBranch);
        fsfrmtio.setFsfrmtioCurency(currency);
        fsfrmtio.setFsfrmtioEmpNo(empNo);
        fsfrmtio.setFsfrmtioNewCustomerId(newCustomerId);
        fsfrmtio.setFsfrmtioNewInterbnchNet(newInterbnchNet);
        fsfrmtio.setFsfrmtioNewInterbnchRemit(newInterbnchRemit);
        fsfrmtio.setFsfrmtioOldCustomerId(oldCustomerId);
        fsfrmtio.setFsfrmtioOldInterbnchNet(oldInterbnchNet);
        fsfrmtio.setFsfrmtioOldInterbnchRemit(oldInterbnchRemit);
    }
}
