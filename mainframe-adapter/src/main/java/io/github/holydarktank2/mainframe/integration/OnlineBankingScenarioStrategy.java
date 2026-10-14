package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionFoqapi00OuterRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;

public interface OnlineBankingScenarioStrategy {
    void applyScenarioFields(AddTransactionOuterRequest outerRequest,
                             AddTransactionFoqapi00OuterRequest foqapi00,
                             OnlineBankingEnrichedContext context);
}
