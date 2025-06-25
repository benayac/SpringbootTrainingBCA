package com.bcatraining.helper.handler;

public class RequestNotValidException extends RuntimeException{
    public RequestNotValidException() {
        super("Request Format Invalid");
    }
}
