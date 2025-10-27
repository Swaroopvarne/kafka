package com.modefin.monitorservice.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modefin.monitorservice.dto.RegistrationRequest;
import com.modefin.monitorservice.dto.RegistrationResponse;
import com.modefin.monitorservice.emailService.RetailRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {

	private final RetailRegistrationService registrationService;

	@PostMapping
	public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest req) {
		String otp = registrationService.registerAndSendOtp(req);
		return ResponseEntity.accepted()
				.body(new RegistrationResponse("OTP sent (check logs/notification-service)", otp));
	}
}
