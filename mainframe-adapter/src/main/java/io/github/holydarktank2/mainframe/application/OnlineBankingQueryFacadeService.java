package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.application.context.OnlineBankingContextResolver;
import io.github.holydarktank2.mainframe.application.context.OnlineBankingEnrichedContext;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.OnlineBankingQueryScenarioStrategy;
import io.github.holydarktank2.mainframe.integration.OnlineBankingRequestAssembler;
import io.github.holydarktank2.mainframe.integration.OnlineBankingResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Service;

@Service
public class OnlineBankingQueryFacadeService {
    private final OnlineBankingContextResolver contextResolver;
    private final OnlineBankingRequestAssembler requestAssembler;
    private final OnlineBankingQueryScenarioStrategy scenarioStrategy;
    private final OnlineBankingResponseMapper responseMapper;
    private final BankCenterTransactionClient bankCenterTransactionClient;

    public OnlineBankingQueryFacadeService(OnlineBankingContextResolver contextResolver,
                                           OnlineBankingRequestAssembler requestAssembler,
                                           OnlineBankingQueryScenarioStrategy scenarioStrategy,
                                           OnlineBankingResponseMapper responseMapper,
                                           BankCenterTransactionClient bankCenterTransactionClient) {
        this.contextResolver = contextResolver;
        this.requestAssembler = requestAssembler;
        this.scenarioStrategy = scenarioStrategy;
        this.responseMapper = responseMapper;
        this.bankCenterTransactionClient = bankCenterTransactionClient;
    }

    public OnlineBankingQueryFrontendResponse process(OnlineBankingQueryFrontendRequest frontendRequest) {
        OnlineBankingEnrichedContext context = contextResolver.resolve(frontendRequest);
        AddTransactionOuterResponse outerResponse =
                bankCenterTransactionClient.addTransaction(requestAssembler.assemble(context, scenarioStrategy));
        return responseMapper.mapQuery(outerResponse);
    }
}
