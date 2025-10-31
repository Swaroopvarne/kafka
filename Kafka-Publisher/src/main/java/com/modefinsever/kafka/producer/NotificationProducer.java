package com.modefinsever.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.modefinsever.kafka.request.NotificationComReq;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public NotificationProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendSms(String topic, NotificationComReq message) {
		try {
			log.info("Sending SMS message to topic {}: {}", topic, message);
			ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);

			future.addCallback(new ListenableFutureCallback<>() {
				@Override
				public void onSuccess(SendResult<String, Object> result) {
					log.info(" SMS sent successfully to topic {} with offset {}", topic,
							result.getRecordMetadata().offset());
				}

				@Override
				public void onFailure(Throwable ex) {
					log.error("Failed to send SMS to topic {}: {}", topic, ex.getMessage(), ex);
					throw new RuntimeException("Kafka SMS send failure: " + ex.getMessage(), ex);
				}
			});
		} catch (Exception e) {
			log.error("⚠️ Exception while producing SMS message: {}", e.getMessage(), e);
			throw new RuntimeException("Error while sending SMS message to Kafka", e);
		}
	}

	public void sendEmail(String topic, NotificationComReq message) {
		try {
			log.info(" Sending Email message to topic {}: {}", topic, message);
			ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);

			future.addCallback(new ListenableFutureCallback<>() {
				@Override
				public void onSuccess(SendResult<String, Object> result) {
					log.info(" Email sent successfully to topic {} with offset {}", topic,
							result.getRecordMetadata().offset());
				}

				@Override
				public void onFailure(Throwable ex) {
					log.error(" Failed to send Email to topic {}: {}", topic, ex.getMessage(), ex);
					throw new RuntimeException("Kafka Email send failure: " + ex.getMessage(), ex);
				}
			});
		} catch (Exception e) {
			log.error(" Exception while producing Email message: {}", e.getMessage(), e);
			throw new RuntimeException("Error while sending Email message to Kafka", e);
		}
	}
}
