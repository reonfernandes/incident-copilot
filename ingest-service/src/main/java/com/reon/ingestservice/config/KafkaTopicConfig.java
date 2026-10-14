package com.reon.ingestservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private final String rawTopic;

    public KafkaTopicConfig(@Value("${incident-copilot.kafka.raw-topic}") String rawTopic) {
        this.rawTopic = rawTopic;
    }

    @Bean
    public NewTopic incident() {
        return TopicBuilder
                .name(rawTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
