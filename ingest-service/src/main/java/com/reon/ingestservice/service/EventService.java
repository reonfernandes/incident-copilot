package com.reon.ingestservice.service;

import com.reon.ingestservice.dto.ErrorEventRequest;
import com.reon.ingestservice.dto.response.IngestResponse;

public interface EventService {
    IngestResponse ingest(ErrorEventRequest request);
}
