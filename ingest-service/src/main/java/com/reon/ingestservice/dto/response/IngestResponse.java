package com.reon.ingestservice.dto.response;

import com.reon.ingestservice.enums.IngestStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record IngestResponse(
        UUID eventId,
        IngestStatus status
) {
}

