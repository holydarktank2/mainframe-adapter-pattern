package io.github.holydarktank2.mainframe.controller.dto;

import java.math.BigDecimal;

public record ForwardRemittanceFrontendRequest(
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
        String busType,
        String depType,
        String refNo,
        String txCod,
        String processBranch,
        String inwardBranch,
        String currency,
        String newCustomerId,
        BigDecimal newInterbnchNet,
        BigDecimal newInterbnchRemit,
        String oldCustomerId,
        BigDecimal oldInterbnchNet,
        BigDecimal oldInterbnchRemit
) {
}
