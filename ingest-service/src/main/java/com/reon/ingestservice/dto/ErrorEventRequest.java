package com.reon.ingestservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record ErrorEventRequest(
        @NotNull(message = "Event Id is required")
        UUID eventId,

        @NotBlank(message = "Service name is required")
        @Size(max = 255, message = "Max 255 characters for service name")
        String serviceName,

        @NotBlank(message = "Exception type is required")
        @Size(max = 255, message = "Max 255 characters for exception type")
        String exceptionType,

        String message,
        String stackTrace,

        @NotNull(message = "Timing for occurred at is required")
        Instant occurredAt

) {
}
