package com.reon.ingestservice.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record DeploymentResponse(
        Long id,
        String serviceName,
        String version,
        Instant deployedAt
) {
}
