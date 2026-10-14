package io.github.holydarktank2.mainframe.domain;

import io.github.holydarktank2.mainframe.core.FieldMapping;
import io.github.holydarktank2.mainframe.core.GenericBuilder;

import java.math.BigDecimal;

public class SpotRemittanceRequest {
    @FieldMapping(section = "basic", field = "refNo", required = true)
    private String refNo;

    @FieldMapping(section = "remittance", field = "beneficiaryAccount", required = true)
    private String beneficiaryAccount;

    @FieldMapping(section = "remittance", field = "currency", required = true)
    private String currency;

    @FieldMapping(section = "remittance", field = "amount", required = true)
    private BigDecimal amount;

    public static Builder builder() {
        return new Builder();
    }

    public String getRefNo() {
        return refNo;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private final GenericBuilder<SpotRemittanceRequest> delegate = GenericBuilder.of(SpotRemittanceRequest::new);

        public Builder refNo(String refNo) {
            delegate.with((request, value) -> request.refNo = value, refNo);
            return this;
        }

        public Builder beneficiaryAccount(String beneficiaryAccount) {
            delegate.with((request, value) -> request.beneficiaryAccount = value, beneficiaryAccount);
            return this;
        }

        public Builder currency(String currency) {
            delegate.with((request, value) -> request.currency = value, currency);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            delegate.with((request, value) -> request.amount = value, amount);
            return this;
        }

        public SpotRemittanceRequest build() {
            return delegate.build();
        }
    }
}
