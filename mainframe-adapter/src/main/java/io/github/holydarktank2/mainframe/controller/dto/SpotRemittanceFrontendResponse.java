package io.github.holydarktank2.mainframe.controller.dto;

public record SpotRemittanceFrontendResponse(
        boolean success,
        String returnCode,
        String errorMessage,
        String txnNo,
        String serialNo
) {
}
