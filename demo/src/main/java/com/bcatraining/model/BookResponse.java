package com.bcatraining.model;

import com.bcatraining.model.entity.Book;

public class BookResponse<T> {
    private String message;
    private T data;

    public BookResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
