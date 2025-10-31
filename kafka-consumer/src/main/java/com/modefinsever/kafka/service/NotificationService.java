package com.modefinsever.kafka.service;


import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modefinsever.kafka.entity.SmsMesg;
import com.modefinsever.kafka.repo.SmsMesgRepository;
import com.modefinsever.kafka.request.NotificationComReq;
import com.modefinsever.kafka.utils.SendHTTPSMS;
import com.modefinsever.kafka.utils.SendMail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {

    private final SmsMesgRepository smsMesgRepository;

    public NotificationService(SmsMesgRepository smsMesgRepository) {
        this.smsMesgRepository = smsMesgRepository;
    }

    @Transactional
    public void processNotification(NotificationComReq request) {
        if ("SMS".equalsIgnoreCase(request.getType())) {
//        	processSms(request.getToAddress(), request.getMesg());
        } else if ("MAIL".equalsIgnoreCase(request.getType())) {
//        	processEmail(request.getToAddress(), request.getSubject(), request.getMesg());
        } else {
            log.warn("❌ Unknown notification type: {}", request.getType());
        }
    }
    public void processSms(NotificationComReq  messageContent) {
        try {
            log.info("📲 Sending SMS: {}", messageContent);

            // Send actual SMS using your class
            SendHTTPSMS sender = new SendHTTPSMS();
            HashMap<String, Object> map = new HashMap<>();
            map.put("MobileNumber",messageContent.getToAddress()); 
            map.put("Message", messageContent);
//            sender.sendSMS(map);

         // Save to DB
            SmsMesg sms = SmsMesg.builder()
                    .toAddress(messageContent.getToAddress())
                    .message(messageContent.getMesg())
                    .status(1)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .build();

            smsMesgRepository.save(sms);

            log.info("✅ SMS sent and saved to DB successfully");

        } catch (Exception e) {
            log.error("❌ Failed to send SMS: {}", e.getMessage(), e);
        }
    }

    @Transactional
    public void processEmail(NotificationComReq  emailContent) {
        try {
            log.info("📧 Sending Email: {}", emailContent);

            // Send actual Email using your class
            SendMail mailer = new SendMail();
            HashMap<String, Object> map = new HashMap<>();
            map.put("ToAddress", emailContent.getToAddress());
            map.put("Subject", emailContent.getSubject());
            map.put("MailMessage", emailContent.getMesg());
            mailer.sendMail(map);

         // Save to DB
            SmsMesg emailRecord = SmsMesg.builder()
                    .toAddress((String) map.get("ToAddress"))
                    .message((String) map.get("MailMessage"))
                    .status(1)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .build();

            smsMesgRepository.save(emailRecord);

            log.info("✅ Email sent and logged successfully");

        } catch (Exception e) {
            log.error("❌ Failed to send email: {}", e.getMessage(), e);
        }
    }
}

