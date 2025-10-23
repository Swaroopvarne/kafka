package com.modefinsever.kafka.ApiResponse;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private T data;

	public ApiResponse() {
		this.timestamp = LocalDateTime.now();
	}

	public ApiResponse(int status, String message, T data) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<>(200, message, data);
	}

	public static <T> ApiResponse<T> error(int status, String message) {
		return new ApiResponse<>(status, message, null);
	}

}
