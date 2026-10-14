package io.github.holydarktank2.mainframe.service;

import io.github.holydarktank2.mainframe.converter.ForeignExchangeConverter;
import io.github.holydarktank2.mainframe.converter.ForeignExchangeResponseConverter;
import io.github.holydarktank2.mainframe.converter.ForwardRemittanceConverter;
import io.github.holydarktank2.mainframe.converter.ForwardRemittanceResponseConverter;
import io.github.holydarktank2.mainframe.converter.OnlineBankingConverter;
import io.github.holydarktank2.mainframe.converter.OnlineBankingResponseConverter;
import io.github.holydarktank2.mainframe.converter.SpotRemittanceConverter;
import io.github.holydarktank2.mainframe.converter.SpotRemittanceResponseConverter;
import io.github.holydarktank2.mainframe.domain.ForeignExchangeRequest;
import io.github.holydarktank2.mainframe.domain.ForwardRemittanceRequest;
import io.github.holydarktank2.mainframe.domain.OnlineBankingRequest;
import io.github.holydarktank2.mainframe.domain.SpotRemittanceRequest;
import io.github.holydarktank2.mainframe.domain.response.ForeignExchangeResponse;
import io.github.holydarktank2.mainframe.domain.response.ForwardRemittanceResponse;
import io.github.holydarktank2.mainframe.domain.response.OnlineBankingResponse;
import io.github.holydarktank2.mainframe.domain.response.SpotRemittanceResponse;
import io.github.holydarktank2.mainframe.outer.BankApiClient;

public class SimpleBankTransactionService {
    private final ForwardRemittanceConverter forwardRequestConverter = new ForwardRemittanceConverter();
    private final SpotRemittanceConverter spotRequestConverter = new SpotRemittanceConverter();
    private final OnlineBankingConverter onlineRequestConverter = new OnlineBankingConverter();
    private final ForeignExchangeConverter forexRequestConverter = new ForeignExchangeConverter();

    private final ForwardRemittanceResponseConverter forwardResponseConverter = new ForwardRemittanceResponseConverter();
    private final SpotRemittanceResponseConverter spotResponseConverter = new SpotRemittanceResponseConverter();
    private final OnlineBankingResponseConverter onlineResponseConverter = new OnlineBankingResponseConverter();
    private final ForeignExchangeResponseConverter forexResponseConverter = new ForeignExchangeResponseConverter();

    public ForwardRemittanceResponse executeForwardRemittance(ForwardRemittanceRequest request, BankApiClient bankApiClient) {
        return forwardResponseConverter.convert(bankApiClient.addTransaction(forwardRequestConverter.convert(request)));
    }

    public SpotRemittanceResponse executeSpotRemittance(SpotRemittanceRequest request, BankApiClient bankApiClient) {
        return spotResponseConverter.convert(bankApiClient.addTransaction(spotRequestConverter.convert(request)));
    }

    public OnlineBankingResponse executeOnlineBanking(OnlineBankingRequest request, BankApiClient bankApiClient) {
        return onlineResponseConverter.convert(bankApiClient.addTransaction(onlineRequestConverter.convert(request)));
    }

    public ForeignExchangeResponse executeForeignExchange(ForeignExchangeRequest request, BankApiClient bankApiClient) {
        return forexResponseConverter.convert(bankApiClient.addTransaction(forexRequestConverter.convert(request)));
    }
}
