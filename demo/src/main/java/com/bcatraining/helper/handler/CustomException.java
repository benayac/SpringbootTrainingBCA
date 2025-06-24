package com.bcatraining.helper.handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {

    private HttpStatus httpStatus;
    private String title;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CustomException(HttpStatus httpStatus, String title, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.title = title;
    }
}
