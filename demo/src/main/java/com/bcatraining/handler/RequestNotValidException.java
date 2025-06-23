package com.bcatraining.handler;

public class RequestNotValidException extends RuntimeException{
    public RequestNotValidException() {
        super("Request Format Invalid");
    }
}
