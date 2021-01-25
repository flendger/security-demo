package ru.flendger.spring.security.securitydemo.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private int status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
