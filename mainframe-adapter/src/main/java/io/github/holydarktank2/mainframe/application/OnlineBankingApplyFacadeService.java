package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.application.context.OnlineBankingContextResolver;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.OnlineBankingApplyScenarioStrategy;
import io.github.holydarktank2.mainframe.integration.OnlineBankingRequestAssembler;
import io.github.holydarktank2.mainframe.integration.OnlineBankingResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Service;

@Service
public class OnlineBankingApplyFacadeService {
    private final OnlineBankingContextResolver contextResolver;
    private final OnlineBankingRequestAssembler requestAssembler;
    private final OnlineBankingApplyScenarioStrategy scenarioStrategy;
    private final OnlineBankingResponseMapper responseMapper;
    private final BankCenterTransactionClient bankCenterTransactionClient;

    public OnlineBankingApplyFacadeService(OnlineBankingContextResolver contextResolver,
                                           OnlineBankingRequestAssembler requestAssembler,
                                           OnlineBankingApplyScenarioStrategy scenarioStrategy,
                                           OnlineBankingResponseMapper responseMapper,
                                           BankCenterTransactionClient bankCenterTransactionClient) {
        this.contextResolver = contextResolver;
        this.requestAssembler = requestAssembler;
        this.scenarioStrategy = scenarioStrategy;
        this.responseMapper = responseMapper;
        this.bankCenterTransactionClient = bankCenterTransactionClient;
    }

    public OnlineBankingApplyFrontendResponse process(OnlineBankingApplyFrontendRequest frontendRequest) {
        OnlineBankingEnrichedContext context = contextResolver.resolve(frontendRequest);
        AddTransactionOuterResponse outerResponse =
                bankCenterTransactionClient.addTransaction(requestAssembler.assemble(context, scenarioStrategy));
        return responseMapper.mapApply(outerResponse);
    }
}
