package com.reon.ingestservice.messaging.impl;

import com.reon.ingestservice.dto.RawEventMessage;
import com.reon.ingestservice.messaging.RawEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class RawEventPublisherImpl implements RawEventPublisher {

    private final KafkaTemplate<String, RawEventMessage> kafkaTemplate;
    private final String rawTopic;

    public RawEventPublisherImpl(KafkaTemplate<String, RawEventMessage> kafkaTemplate,
                                 @Value("${incident-copilot.kafka.raw-topic}") String rawTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.rawTopic = rawTopic;
    }

    @Override
    public void publish(RawEventMessage message) {
        String service = message.serviceName();

        log.info("Publishing raw event....");
        CompletableFuture<SendResult<String, RawEventMessage>> send = kafkaTemplate.send(rawTopic, service, message);
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("event published successfully.. {}", result.getRecordMetadata());
            } else {
                log.error("failed to publish event: {}", message.eventId(), ex);
            }
        });

    }
}
