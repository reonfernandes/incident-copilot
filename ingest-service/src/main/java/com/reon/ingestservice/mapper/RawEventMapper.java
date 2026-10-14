package com.reon.ingestservice.mapper;

import com.reon.ingestservice.dto.ErrorEventRequest;
import com.reon.ingestservice.dto.RawEventMessage;
import com.reon.ingestservice.model.RawEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class RawEventMapper {
    public RawEvent mapToEntity(ErrorEventRequest eventRequest, Instant receivedAt) {
        return RawEvent.builder()
                .eventId(eventRequest.eventId())
                .serviceName(eventRequest.serviceName())
                .exceptionType(eventRequest.exceptionType())
                .stackTrace(eventRequest.stackTrace())
                .message(eventRequest.message())
                .occurredAt(eventRequest.occurredAt())
                .receivedAt(receivedAt)
                .build();
    }

    public RawEventMessage toMessage(RawEvent rawEvent) {
        return RawEventMessage.builder()
                .eventId(rawEvent.getEventId())
                .serviceName(rawEvent.getServiceName())
                .exceptionType(rawEvent.getExceptionType())
                .message(rawEvent.getMessage())
                .stackTrace(rawEvent.getStackTrace())
                .occurredAt(rawEvent.getOccurredAt())
                .receivedAt(rawEvent.getReceivedAt())
                .build();
    }
}

