package io.github.holydarktank2.mainframe.application.context;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingFrontendRequest;
import io.github.holydarktank2.mainframe.integration.ebanking.BankMessageProvider;
import io.github.holydarktank2.mainframe.integration.ebanking.CustomerReferenceProvider;
import io.github.holydarktank2.mainframe.integration.ebanking.OtherSystemProvider;
import org.springframework.stereotype.Component;

@Component
public class OnlineBankingContextResolver {
    private final CustomerReferenceProvider customerReferenceProvider;
    private final BankMessageProvider bankMessageProvider;
    private final OtherSystemProvider otherSystemProvider;

    public OnlineBankingContextResolver(CustomerReferenceProvider customerReferenceProvider,
                                        BankMessageProvider bankMessageProvider,
                                        OtherSystemProvider otherSystemProvider) {
        this.customerReferenceProvider = customerReferenceProvider;
        this.bankMessageProvider = bankMessageProvider;
        this.otherSystemProvider = otherSystemProvider;
    }

    public OnlineBankingEnrichedContext resolve(OnlineBankingFrontendRequest frontendRequest) {
        try {
            CustomerReferenceData customerReferenceData =
                    customerReferenceProvider.fetch(frontendRequest.applicantId(), frontendRequest.processBrnNo());
            BankMessageData bankMessageData = bankMessageProvider.fetch(frontendRequest.fxNo());
            OtherSystemData otherSystemData =
                    otherSystemProvider.fetch(frontendRequest.businessCod(), frontendRequest.tradeCode());
            return new OnlineBankingEnrichedContext(frontendRequest, customerReferenceData, bankMessageData, otherSystemData);
        } catch (RuntimeException exception) {
            throw new EbankingContextEnrichmentException("Failed to enrich online banking context", exception);
        }
    }
}
