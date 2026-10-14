package com.reon.ingestservice.service;

import com.reon.ingestservice.dto.DeploymentRequest;
import com.reon.ingestservice.dto.response.DeploymentResponse;

public interface DeploymentService {
    DeploymentResponse record(DeploymentRequest request);
}
