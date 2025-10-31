package com.modefinsever.kafka.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.modefinsever.kafka.ApiResponse.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Handle validation errors (if you add @Valid on request bodies later)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", "Validation Error");
		response.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		log.warn("Validation failed: {}", response.get("message"));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Handle type mismatch (e.g., wrong query param type)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("error", "Type Mismatch");
		response.put("message", "Invalid value for parameter: " + ex.getName());
		log.warn("Type mismatch: {}", ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Handle all unhandled exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex, WebRequest request) {
		log.error("Unhandled Exception: {}", ex.getMessage(), ex);
		ApiResponse<Object> response = ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	// Handle specific custom exceptions (you can extend this later)
	@ExceptionHandler(KafkaMessageSendException.class)
	public ResponseEntity<ApiResponse<Object>> handleKafkaException(KafkaMessageSendException ex) {
		log.error("Kafka send failed: {}", ex.getMessage());
		ApiResponse<Object> response = ApiResponse.error(HttpStatus.SERVICE_UNAVAILABLE.value(),
				"Kafka Send Failure: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}
}
