package com.reon.ingestservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;

@Builder
public record DeploymentRequest(
        @NotBlank(message = "Service name cannot be blank")
        @Size(max = 255, message = "Max service name for 255 characters")
        String serviceName,

        @NotBlank(message = "Version is required")
        @Size(max = 100, message = "Max version size of 100 characters")
        String version,

        @PastOrPresent(message = "Deployment can't happen in the future")
        Instant deployedAt
) {
}
