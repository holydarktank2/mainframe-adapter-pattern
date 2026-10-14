package io.github.holydarktank2.mainframe.integration.ebanking;

import io.github.holydarktank2.mainframe.application.context.CustomerReferenceData;
import org.springframework.stereotype.Component;

@Component
public class StubCustomerReferenceProvider implements CustomerReferenceProvider {
    @Override
    public CustomerReferenceData fetch(String applicantId, String processBrnNo) {
        return new CustomerReferenceData("822", "TGIC-" + applicantId, processBrnNo);
    }
}
