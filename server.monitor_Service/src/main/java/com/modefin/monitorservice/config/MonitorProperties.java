package com.modefin.monitorservice.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "monitor")
public class MonitorProperties {

	private List<AppConfig> apps;
	private String alertEmail;
	private String cron;
	private Mail mail;

	@Data
	public static class Mail {
		private String from;
		private String password;

	}

}
