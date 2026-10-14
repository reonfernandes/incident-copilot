package com.reon.ingestservice.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record RawEventMessage(
        UUID eventId,
        String serviceName,
        String exceptionType,
        String message,
        String stackTrace,
        Instant occurredAt,
        Instant receivedAt
) {
}
