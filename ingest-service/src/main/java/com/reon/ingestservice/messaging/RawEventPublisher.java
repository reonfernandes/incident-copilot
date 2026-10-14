package com.reon.ingestservice.messaging;

import com.reon.ingestservice.dto.RawEventMessage;

public interface RawEventPublisher {
    void publish(RawEventMessage message);
}
