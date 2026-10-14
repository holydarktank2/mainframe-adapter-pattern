package io.github.holydarktank2.mainframe.application.context;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingFrontendRequest;

public record OnlineBankingEnrichedContext(
        OnlineBankingFrontendRequest frontendRequest,
        CustomerReferenceData customerReferenceData,
        BankMessageData bankMessageData,
        OtherSystemData otherSystemData
) {
}
