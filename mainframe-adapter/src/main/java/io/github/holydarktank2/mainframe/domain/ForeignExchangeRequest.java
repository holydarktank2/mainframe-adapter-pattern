package io.github.holydarktank2.mainframe.domain;

import io.github.holydarktank2.mainframe.core.FieldMapping;
import io.github.holydarktank2.mainframe.core.GenericBuilder;

import java.math.BigDecimal;

public class ForeignExchangeRequest {
    @FieldMapping(section = "basic", field = "refNo", required = true)
    private String refNo;

    @FieldMapping(section = "forex", field = "fromCurrency", required = true)
    private String fromCurrency;

    @FieldMapping(section = "forex", field = "toCurrency", required = true)
    private String toCurrency;

    @FieldMapping(section = "forex", field = "amount", required = true)
    private BigDecimal amount;

    public static Builder builder() {
        return new Builder();
    }

    public String getRefNo() {
        return refNo;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private final GenericBuilder<ForeignExchangeRequest> delegate = GenericBuilder.of(ForeignExchangeRequest::new);

        public Builder refNo(String refNo) {
            delegate.with((request, value) -> request.refNo = value, refNo);
            return this;
        }

        public Builder fromCurrency(String fromCurrency) {
            delegate.with((request, value) -> request.fromCurrency = value, fromCurrency);
            return this;
        }

        public Builder toCurrency(String toCurrency) {
            delegate.with((request, value) -> request.toCurrency = value, toCurrency);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            delegate.with((request, value) -> request.amount = value, amount);
            return this;
        }

        public ForeignExchangeRequest build() {
            return delegate.build();
        }
    }
}
