package io.github.holydarktank2.mainframe.integration.ebanking;

import io.github.holydarktank2.mainframe.application.context.OtherSystemData;

public interface OtherSystemProvider {
    OtherSystemData fetch(String businessCod, String tradeCode);
}
