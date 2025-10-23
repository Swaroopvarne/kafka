package com.modefinsever.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.modefinsever.kafka.request.NotificationComReq;
import com.modefinsever.kafka.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationConsumer {

    private final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "${app.topics.sms}", groupId = "sms-group")
    public void consumeSms(@Payload NotificationComReq message) {
        try {
            log.info("📨 Received SMS message: {}", message);
            notificationService.processSms(message);
        } catch (Exception e) {
            log.error("❌ Error processing SMS message: {}", e.getMessage(), e);
            // Optionally: send to a dead-letter topic or save in retry queue
        }
    }

    @KafkaListener(topics = "${app.topics.email}", groupId = "email-group")
    public void consumeEmail(@Payload NotificationComReq message) {
        try {
            log.info("📨 Received Email message: {}", message);
            notificationService.processEmail(message);
        } catch (Exception e) {
            log.error("❌ Error processing Email message: {}", e.getMessage(), e);
            // Optionally: dead-letter handling
        }
    }
}
