package com.modefin.monitorservice.emailService;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.modefin.monitorservice.dto.NotificationRequest;
import com.modefin.monitorservice.dto.RegistrationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RetailRegistrationService {

    private final NotificationProducerService producer;
    
    @Value("${app.kafka.topic.email}")
    private String emailTopic;
    
    @Value("${app.kafka.topic.sms}")
    private String smsTopic;

    // returns generated OTP
	public String registerAndSendOtp(RegistrationRequest req) {
		// Here you would save customer to DB; omitted for brevity


        // Generate 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(1_000_000));

        // Build notification request for SMS
        NotificationRequest smsNotification = NotificationRequest.builder()
                .type("SMS")
                .templateCode("OTP_TEMPLATE")             // template name expected by notification-service
                .to(req.getPhoneNumber())
                .params(Map.of(
                        "customerName", req.getCustomerName(),
                        "otp", otp
                ))
                .build();

        // Optionally send email too if provided
        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            NotificationRequest emailNotification = NotificationRequest.builder()
                    .type("EMAIL")
                    .templateCode("OTP_EMAIL_TEMPLATE")
                    .to(req.getEmail())
                    .params(Map.of(
                            "customerName", req.getCustomerName(),
                            "otp", otp
                    ))
                    .build();
            producer.publish(emailNotification);
        }

        // Publish SMS event
        producer.publish(smsNotification);

        // return OTP for testing. Remove in production.
        return otp;
    }
}
