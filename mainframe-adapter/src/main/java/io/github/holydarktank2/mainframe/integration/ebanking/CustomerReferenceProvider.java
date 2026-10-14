package io.github.holydarktank2.mainframe.integration.ebanking;

import io.github.holydarktank2.mainframe.application.context.CustomerReferenceData;

public interface CustomerReferenceProvider {
    CustomerReferenceData fetch(String applicantId, String processBrnNo);
}
