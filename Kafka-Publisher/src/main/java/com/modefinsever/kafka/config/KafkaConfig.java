package com.modefinsever.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${app.topics.sms}")
    private String smsTopic;

    @Value("${app.topics.email}")
    private String emailTopic;

    @Bean
    public NewTopic smsTopic() {
        return TopicBuilder.name(smsTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic emailTopic() {
        return TopicBuilder.name(emailTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
