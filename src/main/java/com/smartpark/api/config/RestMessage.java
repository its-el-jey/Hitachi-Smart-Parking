package com.smartpark.api.config;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


/**
 * @author Loida Jane Anfone <anfoneloidajane@gmail.com>
 */
public class RestMessage {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private Object data;

    public RestMessage(String message, Object data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}


