package com.modefin.monitorservice.config;

import lombok.Data;

@Data
public class AppConfig {
	private String host;
	private int port;
	private String name;

	public AppConfig(String host, int port, String name) {
		this.host = host;
		this.port = port;
		this.name = name;
	}

}