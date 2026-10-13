package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.SpotRemittanceFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.SpotRemittanceFrontendResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remittance")
public class SpotRemittanceController {
    private final SpotRemittanceFacadeService facadeService;

    public SpotRemittanceController(SpotRemittanceFacadeService facadeService) {
        this.facadeService = facadeService;
    }

    @PostMapping("/spot")
    public ResponseEntity<SpotRemittanceFrontendResponse> submit(@RequestBody SpotRemittanceFrontendRequest request) {
        return ResponseEntity.ok(facadeService.process(request));
    }
}
