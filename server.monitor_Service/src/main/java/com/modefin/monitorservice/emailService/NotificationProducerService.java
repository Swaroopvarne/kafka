package com.modefin.monitorservice.emailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.modefin.monitorservice.dto.NotificationRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducerService {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${app.kafka.topic.notification}")
	private String topic;

	public void publish(NotificationRequest request) {
		kafkaTemplate.send(topic, request).thenAccept(result -> {
			log.info("✅ Published notification to topic '{}' partition {} offset {}",
					result.getRecordMetadata().topic(),
					result.getRecordMetadata().partition(),
					result.getRecordMetadata().offset());
		}).exceptionally(ex -> {
			log.error("❌ Failed to publish notification event: {}", ex.getMessage(), ex);
			return null;
		});
	}
}
