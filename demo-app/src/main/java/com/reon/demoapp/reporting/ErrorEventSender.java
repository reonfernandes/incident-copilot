package com.reon.demoapp.reporting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ErrorEventSender {

    private final IngestClient client;

    public ErrorEventSender(IngestClient client) {
        this.client = client;
    }

    @Async
    public void send(ErrorEvent event) {
        try {
            log.info("Sending an event....");
            client.send(event);
        } catch (Exception exception) {
            log.warn("Ingest is down: {}", exception.getMessage());
        }
    }
}
