package io.github.holydarktank2.mainframe.controller.dto;

import java.math.BigDecimal;

public record OnlineBankingApplyFrontendRequest(
        String txnCode,
        String txnUniqueKey,
        String processBrnNo,
        String processBrnId,
        String processBrnType,
        String processMod,
        String empNo,
        String opId,
        String termId,
        String businessDate,
        String applicantId,
        String funcCod,
        String businessCod,
        String inOutward,
        String fxNo,
        String noticeNo,
        String specBank,
        String docuNo,
        String qualify,
        String birthDate,
        String issueDate,
        String expireDate,
        String countryNo,
        String spotFxCurrency,
        BigDecimal spotFxAmount,
        BigDecimal spotNtAmount,
        BigDecimal spotToUsdAmt,
        String contractNo,
        String buySell,
        String tradeCode,
        String attributeCode,
        BigDecimal toUsFxRate,
        String useOfFunds
) {
}
