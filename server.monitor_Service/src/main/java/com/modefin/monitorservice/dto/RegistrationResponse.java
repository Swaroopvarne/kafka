package com.modefin.monitorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponse {
	private String message;
	private String otp; // returned for dev/testing (remove in prod)
}
