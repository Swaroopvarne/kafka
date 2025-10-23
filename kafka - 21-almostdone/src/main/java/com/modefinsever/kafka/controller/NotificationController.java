package com.modefinsever.kafka.controller;

import static com.modefinsever.kafka.utils.UrlsConstants.NOTIFICATON_URL;
import static com.modefinsever.kafka.utils.UrlsConstants.SEND_EMAIL;
import static com.modefinsever.kafka.utils.UrlsConstants.SEND_SMS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modefinsever.kafka.ApiResponse.ApiResponse;
import com.modefinsever.kafka.exception.KafkaMessageSendException;
import com.modefinsever.kafka.producer.NotificationProducer;
import com.modefinsever.kafka.request.NotificationComReq;

@RestController
@RequestMapping(NOTIFICATON_URL)
public class NotificationController {

	private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

	private final NotificationProducer producer;

	@Value("${app.topics.sms}")
	private String smsTopic;

	@Value("${app.topics.email}")
	private String emailTopic;

	public NotificationController(NotificationProducer producer) {
		this.producer = producer;
	}

	@PostMapping(SEND_SMS)
	public ResponseEntity<ApiResponse<?>> sendSms(@RequestBody NotificationComReq message) {
		try {
			log.info("Sending SMS message: {}", message);
			producer.sendSms(smsTopic, message);
			ApiResponse<String> response = ApiResponse.success("SMS message sent successfully.", smsTopic);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("Error while sending SMS message to Kafka: {}", e.getMessage(), e);
			throw new KafkaMessageSendException("Failed to send SMS message to Kafka topic: " + smsTopic, e);

		}
	}

	@PostMapping(SEND_EMAIL)
	public ResponseEntity<ApiResponse<?>> sendEmail(@RequestBody NotificationComReq message) {
		try {
			log.info("Sending Email message: {}", message);
			producer.sendEmail(emailTopic, message);
			ApiResponse<String> response = ApiResponse.success("Email message sent to Kafka topic:", emailTopic);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.error("❌ Error while sending Email message to Kafka: {}", e.getMessage(), e);
			throw new KafkaMessageSendException("Failed to send SMS message to Kafka topic: " + emailTopic, e);
		}
	}
}
