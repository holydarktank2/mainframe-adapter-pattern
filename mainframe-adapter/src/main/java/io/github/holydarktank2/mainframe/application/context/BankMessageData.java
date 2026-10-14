package io.github.holydarktank2.mainframe.application.context;

public record BankMessageData(
        String serialNo,
        String reasonCode,
        String noRepFlag
) {
}
