package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.OnlineBankingApplyRequestAssembler;
import io.github.holydarktank2.mainframe.integration.OnlineBankingApplyResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Service;

@Service
public class OnlineBankingApplyFacadeService {
    private final OnlineBankingApplyRequestAssembler requestAssembler;
    private final OnlineBankingApplyResponseMapper responseMapper;
    private final BankCenterTransactionClient bankCenterTransactionClient;

    public OnlineBankingApplyFacadeService(OnlineBankingApplyRequestAssembler requestAssembler,
                                           OnlineBankingApplyResponseMapper responseMapper,
                                           BankCenterTransactionClient bankCenterTransactionClient) {
        this.requestAssembler = requestAssembler;
        this.responseMapper = responseMapper;
        this.bankCenterTransactionClient = bankCenterTransactionClient;
    }

    public OnlineBankingApplyFrontendResponse process(OnlineBankingApplyFrontendRequest frontendRequest) {
        AddTransactionOuterResponse outerResponse = bankCenterTransactionClient.addTransaction(requestAssembler.assemble(frontendRequest));
        return responseMapper.map(outerResponse);
    }
}
