package io.github.holydarktank2.mainframe.integration.ebanking;

import io.github.holydarktank2.mainframe.application.context.BankMessageData;

public interface BankMessageProvider {
    BankMessageData fetch(String fxNo);
}
