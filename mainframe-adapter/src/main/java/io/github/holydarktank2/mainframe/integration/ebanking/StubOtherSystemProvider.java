package io.github.holydarktank2.mainframe.integration.ebanking;

import io.github.holydarktank2.mainframe.application.context.OtherSystemData;
import org.springframework.stereotype.Component;

@Component
public class StubOtherSystemProvider implements OtherSystemProvider {
    @Override
    public OtherSystemData fetch(String businessCod, String tradeCode) {
        return new OtherSystemData("Y", businessCod + "-" + tradeCode);
    }
}
