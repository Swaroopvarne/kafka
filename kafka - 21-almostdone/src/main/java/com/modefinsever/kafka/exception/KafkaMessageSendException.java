package com.modefinsever.kafka.exception;

public class KafkaMessageSendException extends RuntimeException {
    public KafkaMessageSendException(String message) {
        super(message);
    }

    public KafkaMessageSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
