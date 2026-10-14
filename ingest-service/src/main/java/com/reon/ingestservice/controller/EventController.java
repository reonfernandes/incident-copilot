package com.reon.ingestservice.controller;

import com.reon.ingestservice.dto.ErrorEventRequest;
import com.reon.ingestservice.dto.response.IngestResponse;
import com.reon.ingestservice.service.EventService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@Slf4j
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<IngestResponse> receive(@Valid @RequestBody ErrorEventRequest request) {
        log.info("Incoming request...");
        IngestResponse response = eventService.ingest(request);
        return ResponseEntity.accepted().body(response);
    }
}
