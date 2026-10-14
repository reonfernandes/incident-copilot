package com.reon.ingestservice.mapper;

import com.reon.ingestservice.dto.DeploymentRequest;
import com.reon.ingestservice.dto.response.DeploymentResponse;
import com.reon.ingestservice.model.Deployment;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DeploymentMapper {
    public Deployment mapToEntity(DeploymentRequest request, Instant deployedAt) {
        return Deployment.builder()
                .serviceName(request.serviceName())
                .version(request.version())
                .deployedAt(deployedAt)
                .build();
    }

    public DeploymentResponse toResponse(Deployment deployment) {
        return DeploymentResponse.builder()
                .id(deployment.getId())
                .serviceName(deployment.getServiceName())
                .version(deployment.getVersion())
                .deployedAt(deployment.getDeployedAt())
                .build();
    }
}
