package io.github.holydarktank2.mainframe.controller;

import io.github.holydarktank2.mainframe.application.OnlineBankingQueryFacadeService;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendRequest;
import io.github.holydarktank2.mainframe.controller.dto.OnlineBankingQueryFrontendResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ebanking")
public class OnlineBankingQueryController {
    private final OnlineBankingQueryFacadeService facadeService;

    public OnlineBankingQueryController(OnlineBankingQueryFacadeService facadeService) {
        this.facadeService = facadeService;
    }

    @PostMapping("/query")
    public ResponseEntity<OnlineBankingQueryFrontendResponse> submit(@RequestBody OnlineBankingQueryFrontendRequest request) {
        return ResponseEntity.ok(facadeService.process(request));
    }
}
