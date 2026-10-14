package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.ForwardRemittanceFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.ForwardRemittanceFrontendResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remittance")
public class ForwardRemittanceController {
    private final ForwardRemittanceFacadeService facadeService;

    public ForwardRemittanceController(ForwardRemittanceFacadeService facadeService) {
        this.facadeService = facadeService;
    }

    @PostMapping("/forward")
    public ResponseEntity<ForwardRemittanceFrontendResponse> submit(@RequestBody ForwardRemittanceFrontendRequest request) {
        return ResponseEntity.ok(facadeService.process(request));
    }
}
