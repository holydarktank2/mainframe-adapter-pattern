package io.github.holydarktank2.mainframe.core;

import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;

public interface TransactionConverter<T> {
    AddTransactionOuterRequest convert(T request);
}
