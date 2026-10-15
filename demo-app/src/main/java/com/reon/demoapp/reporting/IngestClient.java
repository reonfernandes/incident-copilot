package com.reon.demoapp.reporting;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ingest-service",
        url = "${incident-copilot.ingest-url}"
)
public interface IngestClient {
    @PostMapping("/api/v1/events")
    void send(@RequestBody ErrorEvent event);
}
