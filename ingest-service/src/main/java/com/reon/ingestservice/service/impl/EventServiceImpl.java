package com.reon.ingestservice.service.impl;

import com.reon.ingestservice.dto.ErrorEventRequest;
import com.reon.ingestservice.dto.response.IngestResponse;
import com.reon.ingestservice.enums.IngestStatus;
import com.reon.ingestservice.mapper.RawEventMapper;
import com.reon.ingestservice.messaging.RawEventPublisher;
import com.reon.ingestservice.model.RawEvent;
import com.reon.ingestservice.repository.RawEventRepository;
import com.reon.ingestservice.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.UUID;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    private final RawEventRepository rawEventRepository;
    private final RawEventPublisher rawEventPublisher;
    private final RawEventMapper rawEventMapper;
    private final Clock clock;

    public EventServiceImpl(RawEventRepository rawEventRepository, RawEventPublisher rawEventPublisher,
                            RawEventMapper rawEventMapper, Clock clock) {
        this.rawEventRepository = rawEventRepository;
        this.rawEventPublisher = rawEventPublisher;
        this.rawEventMapper = rawEventMapper;
        this.clock = clock;
    }

    @Override
    public IngestResponse ingest(ErrorEventRequest request) {

        UUID eventId = request.eventId();

        log.info("Processing ingest...");
        if (rawEventRepository.existsByEventId(request.eventId())) {
            return new IngestResponse(eventId, IngestStatus.DUPLICATE);
        }

        RawEvent event = rawEventMapper.mapToEntity(request, clock.instant());
        try {
            rawEventRepository.saveAndFlush(event);
            log.info("Raw event saved successfully..");
        } catch (DataIntegrityViolationException exception) {
            log.warn("Duplicate event detected..{}", eventId);
            return IngestResponse.builder()
                    .eventId(eventId)
                    .status(IngestStatus.DUPLICATE)
                    .build();
        }

        log.info("Publishing the raw event..");
        rawEventPublisher.publish(rawEventMapper.toMessage(event));

        return new IngestResponse(eventId, IngestStatus.ACCEPTED);
    }
}
