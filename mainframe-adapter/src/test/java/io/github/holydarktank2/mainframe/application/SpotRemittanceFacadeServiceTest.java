package io.github.holydarktank2.mainframe.application;

import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendResponse;
import io.github.holydarktank2.mainframe.integration.BankCenterTransactionClient;
import io.github.holydarktank2.mainframe.integration.FsfrmtioCommonFieldApplier;
import io.github.holydarktank2.mainframe.integration.OuterRequestCommonFieldApplier;
import io.github.holydarktank2.mainframe.integration.SpotRemittanceRequestAssembler;
import io.github.holydarktank2.mainframe.integration.SpotRemittanceResponseMapper;
import io.github.holydarktank2.mainframe.sampleRequest.AddTransactionOuterResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpotRemittanceFacadeServiceTest {

    @Test
    void shouldProcessSpotRemittanceFlow() {
        BankCenterTransactionClient client = request -> {
            AddTransactionOuterResponse response = new AddTransactionOuterResponse();
            response.setOutReturnCode("0000");
            response.setOutTxnNo("TXN-SPOT");
            response.setOutSerialNo("SER-SPOT");
            return response;
        };

        SpotRemittanceFacadeService service = new SpotRemittanceFacadeService(
                new SpotRemittanceRequestAssembler(new OuterRequestCommonFieldApplier(), new FsfrmtioCommonFieldApplier()),
                new SpotRemittanceResponseMapper(),
                client
        );

        SpotRemittanceFrontendRequest request = new SpotRemittanceFrontendRequest(
                "S100", "UK002", "002", "0002", "C", "A", "E002", "OP002", "T002", "20261013",
                "02", "D2", "REF002", "TX02", "003", "004", "JPY", "CUST101",
                new BigDecimal("90"), new BigDecimal("95"), "CUST201",
                new BigDecimal("70"), new BigDecimal("72"), "Y"
        );

        SpotRemittanceFrontendResponse result = service.process(request);

        assertTrue(result.success());
        assertEquals("TXN-SPOT", result.txnNo());
    }
}
