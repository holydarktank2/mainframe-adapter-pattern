package io.github.holydarktank2.mainframe.controller.dto;

import java.math.BigDecimal;

public record ForwardRemittanceFrontendResponse(
        boolean success,
        String returnCode,
        String errorMessage,
        String txnNo,
        String serialNo,
        String noticeNo,
        String customerNo,
        String contractNo1,
        BigDecimal valueFx1,
        BigDecimal valueNt1,
        BigDecimal valueUs1
) {
}
