package io.github.holydarktank2.mainframe.integration.ebanking;

import io.github.holydarktank2.mainframe.application.context.BankMessageData;
import org.springframework.stereotype.Component;

@Component
public class StubBankMessageProvider implements BankMessageProvider {
    @Override
    public BankMessageData fetch(String fxNo) {
        return new BankMessageData("MSG-" + fxNo, "00", "N");
    }
}
