package io.github.holydarktank2.mainframe.integration;

import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterRequest;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;

public interface BankCenterTransactionClient {
    AddTransactionOuterResponse addTransaction(AddTransactionOuterRequest request);
}
