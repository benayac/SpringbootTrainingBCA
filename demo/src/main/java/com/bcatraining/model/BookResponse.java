package com.bcatraining.model;

import com.bcatraining.model.entity.Book;

public class BookResponse {
    private String message;
    private Book data;

    public BookResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getData() {
        return data;
    }

    public void setData(Book data) {
        this.data = data;
    }
}
