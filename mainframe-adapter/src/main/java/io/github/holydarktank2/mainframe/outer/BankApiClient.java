package io.github.holydarktank2.mainframe.outer;

public interface BankApiClient {
    AddTransactionOuterResponse addTransaction(AddTransactionOuterRequest request);
}
