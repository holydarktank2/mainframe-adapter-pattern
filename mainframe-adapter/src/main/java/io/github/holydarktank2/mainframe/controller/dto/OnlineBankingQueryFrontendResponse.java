package io.github.holydarktank2.mainframe.controller.dto;

public record OnlineBankingQueryFrontendResponse(
        boolean success,
        String returnCode,
        String errorMessage,
        String txnNo,
        String backReturnCode,
        String backLogRrn,
        String serialNo
) {
}
