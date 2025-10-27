package com.modefin.monitorservice.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {
	/**
	 * "SMS" or "EMAIL"
	 */
	private String type;

	/**
	 * template code or filename used by notification-service, e.g. "OTP_TEMPLATE"
	 */
	private String templateCode;

	/**
	 * recipient (phone for SMS / email for EMAIL)
	 */
	private String to;

	/**
	 * placeholder values, e.g. {"customerName":"Swaroop","otp":"123456"}
	 */
	private Map<String, String> params;
}
