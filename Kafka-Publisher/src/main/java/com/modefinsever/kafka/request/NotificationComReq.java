package com.modefinsever.kafka.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationComReq {

	private String toAddress;

	private String mesg;

	private String subject;

	private String type;  // EMAIL or SMS
}