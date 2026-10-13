package io.github.holydarktank2.mainframe.core;

import io.github.holydarktank2.mainframe.outer.AddTransactionOuterRequest;

import java.util.Map;

public abstract class AbstractTransactionConverter<T> implements TransactionConverter<T> {

    protected AddTransactionOuterRequest buildOuterRequest(String transactionType, T request) {
        Map<String, Object> mapped = FieldMappingSupport.extractMappedFields(request);

        AddTransactionOuterRequest outerRequest = GenericBuilder.of(AddTransactionOuterRequest::new)
                .with(AddTransactionOuterRequest::setTransactionType, transactionType)
                .build();

        mapped.forEach((path, value) -> {
            String[] parts = path.split("\\.");
            if (parts.length != 2) {
                throw new ConversionException("Invalid mapping path: " + path);
            }
            outerRequest.putSectionField(parts[0], parts[1], value);
        });
        return outerRequest;
    }
}
