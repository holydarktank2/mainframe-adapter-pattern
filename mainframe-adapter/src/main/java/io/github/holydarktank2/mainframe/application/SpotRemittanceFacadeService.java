package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.SpotRemittanceRequestAssembler;
import io.github.holydarktank2.mainframe.integration.SpotRemittanceResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.springframework.stereotype.Service;

@Service
public class SpotRemittanceFacadeService {
    private final SpotRemittanceRequestAssembler requestAssembler;
    private final SpotRemittanceResponseMapper responseMapper;
    private final BankCenterTransactionClient bankCenterTransactionClient;

    public SpotRemittanceFacadeService(SpotRemittanceRequestAssembler requestAssembler,
                                       SpotRemittanceResponseMapper responseMapper,
                                       BankCenterTransactionClient bankCenterTransactionClient) {
        this.requestAssembler = requestAssembler;
        this.responseMapper = responseMapper;
        this.bankCenterTransactionClient = bankCenterTransactionClient;
    }

    public SpotRemittanceFrontendResponse process(SpotRemittanceFrontendRequest frontendRequest) {
        AddTransactionOuterResponse outerResponse = bankCenterTransactionClient.addTransaction(requestAssembler.assemble(frontendRequest));
        return responseMapper.map(outerResponse);
    }
}
