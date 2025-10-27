package com.modefin.monitorservice.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
	private String customerName;

	private String phoneNumber;

	private String email; // optional
}
