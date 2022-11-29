package com.jitpay.userservice.model.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Exception Handler Dto for outbound services
 */
public class ExceptionModel {

    private HttpStatus httpStatus;

    private LocalDateTime timestamp;

    private String message;

    private String details;

    public ExceptionModel() {
    }

    public ExceptionModel(HttpStatus httpStatus, String message, String details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionModel that = (ExceptionModel) o;
        return httpStatus == that.httpStatus && Objects.equals(timestamp, that.timestamp) && Objects.equals(message, that.message) && Objects.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, timestamp, message, details);
    }

    @Override
    public String toString() {
        return "ExceptionModel{" +
                "httpStatus=" + httpStatus +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
