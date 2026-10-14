package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.ForwardRemittanceRequestAssembler;
import io.github.holydarktank2.mainframe.integration.ForwardRemittanceResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Service;

@Service
public class ForwardRemittanceFacadeService {
    private final ForwardRemittanceRequestAssembler requestAssembler;
    private final ForwardRemittanceResponseMapper responseMapper;
    private final BankCenterTransactionClient bankCenterTransactionClient;

    public ForwardRemittanceFacadeService(ForwardRemittanceRequestAssembler requestAssembler,
                                          ForwardRemittanceResponseMapper responseMapper,
                                          BankCenterTransactionClient bankCenterTransactionClient) {
        this.requestAssembler = requestAssembler;
        this.responseMapper = responseMapper;
        this.bankCenterTransactionClient = bankCenterTransactionClient;
    }

    public ForwardRemittanceFrontendResponse process(ForwardRemittanceFrontendRequest frontendRequest) {
        AddTransactionOuterRequest outerRequest = requestAssembler.assemble(frontendRequest);
        AddTransactionOuterResponse outerResponse = bankCenterTransactionClient.addTransaction(outerRequest);
        return responseMapper.map(outerResponse);
    }
}
