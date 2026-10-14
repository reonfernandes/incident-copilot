package com.reon.ingestservice.controller;

import com.reon.ingestservice.dto.DeploymentRequest;
import com.reon.ingestservice.dto.response.DeploymentResponse;
import com.reon.ingestservice.service.DeploymentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deployments")
@Slf4j
public class DeploymentController {

    private final DeploymentService deploymentService;

    public DeploymentController(DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping
    public ResponseEntity<DeploymentResponse> record(@Valid @RequestBody DeploymentRequest request) {
        log.info("Incoming deployment related request..");
        DeploymentResponse record = deploymentService.record(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(record);
    }
}
