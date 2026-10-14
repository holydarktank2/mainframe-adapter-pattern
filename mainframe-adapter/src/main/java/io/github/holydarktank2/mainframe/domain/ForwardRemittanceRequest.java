package io.github.holydarktank2.mainframe.domain;

import io.github.holydarktank2.mainframe.core.FieldMapping;
import io.github.holydarktank2.mainframe.core.GenericBuilder;

import java.math.BigDecimal;

public class ForwardRemittanceRequest {
    @FieldMapping(section = "basic", field = "refNo", required = true)
    private String refNo;

    @FieldMapping(section = "remittance", field = "contractNo", required = true)
    private String contractNo;

    @FieldMapping(section = "remittance", field = "dealType", required = true)
    private String dealType;

    @FieldMapping(section = "remittance", field = "amount", required = true)
    private BigDecimal amount;

    public static Builder builder() {
        return new Builder();
    }

    public String getRefNo() {
        return refNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public String getDealType() {
        return dealType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private final GenericBuilder<ForwardRemittanceRequest> delegate =
                GenericBuilder.of(ForwardRemittanceRequest::new);

        public Builder refNo(String refNo) {
            delegate.with((request, value) -> request.refNo = value, refNo);
            return this;
        }

        public Builder contractNo(String contractNo) {
            delegate.with((request, value) -> request.contractNo = value, contractNo);
            return this;
        }

        public Builder dealType(String dealType) {
            delegate.with((request, value) -> request.dealType = value, dealType);
            return this;
        }

        public Builder amount(BigDecimal amount) {
            delegate.with((request, value) -> request.amount = value, amount);
            return this;
        }

        public ForwardRemittanceRequest build() {
            return delegate.build();
        }
    }
}
