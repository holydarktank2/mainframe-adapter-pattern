package io.github.holydarktank2.mainframe.core;

import io.github.holydarktank2.mainframe.outer.AddTransactionOuterResponse;

public interface TransactionResponseConverter<T> {
    T convert(AddTransactionOuterResponse response);
}
