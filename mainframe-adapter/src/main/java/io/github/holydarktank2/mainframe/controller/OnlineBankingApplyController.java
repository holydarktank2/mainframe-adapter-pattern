package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.OnlineBankingApplyFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingApplyFrontendResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ebanking")
public class OnlineBankingApplyController {
    private final OnlineBankingApplyFacadeService facadeService;

    public OnlineBankingApplyController(OnlineBankingApplyFacadeService facadeService) {
        this.facadeService = facadeService;
    }

    @PostMapping("/apply")
    public ResponseEntity<OnlineBankingApplyFrontendResponse> submit(@RequestBody OnlineBankingApplyFrontendRequest request) {
        return ResponseEntity.ok(facadeService.process(request));
    }
}
