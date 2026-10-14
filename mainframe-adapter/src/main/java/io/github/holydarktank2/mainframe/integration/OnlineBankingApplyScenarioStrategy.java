package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionFoqapi00OuterRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import org.springframework.stereotype.Component;

@Component
public class OnlineBankingApplyScenarioStrategy implements OnlineBankingScenarioStrategy {
    @Override
    public void applyScenarioFields(AddTransactionOuterRequest outerRequest,
                                    AddTransactionFoqapi00OuterRequest foqapi00,
                                    OnlineBankingEnrichedContext context) {
        outerRequest.setBegin8("BEGIN8");
        outerRequest.setPgmName8("FOQAPI00");
        outerRequest.setEnd8("END8");
        foqapi00.setFoqapi00FuncCod("APPLY-" + context.frontendRequest().funcCod());
    }
}
