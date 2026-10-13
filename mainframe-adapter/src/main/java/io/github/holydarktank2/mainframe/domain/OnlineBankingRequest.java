package io.github.holydarktank2.mainframe.domain;

import io.github.holydarktank2.mainframe.core.FieldMapping;
import io.github.holydarktank2.mainframe.core.GenericBuilder;

public class OnlineBankingRequest {
    @FieldMapping(section = "basic", field = "refNo", required = true)
    private String refNo;

    @FieldMapping(section = "onlineBanking", field = "applicantId", required = true)
    private String applicantId;

    @FieldMapping(section = "onlineBanking", field = "email", required = true)
    private String email;

    @FieldMapping(section = "onlineBanking", field = "mobile")
    private String mobile;

    public static Builder builder() {
        return new Builder();
    }

    public String getRefNo() {
        return refNo;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public static class Builder {
        private final GenericBuilder<OnlineBankingRequest> delegate = GenericBuilder.of(OnlineBankingRequest::new);

        public Builder refNo(String refNo) {
            delegate.with((request, value) -> request.refNo = value, refNo);
            return this;
        }

        public Builder applicantId(String applicantId) {
            delegate.with((request, value) -> request.applicantId = value, applicantId);
            return this;
        }

        public Builder email(String email) {
            delegate.with((request, value) -> request.email = value, email);
            return this;
        }

        public Builder mobile(String mobile) {
            delegate.with((request, value) -> request.mobile = value, mobile);
            return this;
        }

        public OnlineBankingRequest build() {
            return delegate.build();
        }
    }
}
