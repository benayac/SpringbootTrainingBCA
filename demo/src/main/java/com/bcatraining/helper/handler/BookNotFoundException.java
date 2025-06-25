package com.bcatraining.helper.handler;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Book with id: " + id + " not found");
    }

    public BookNotFoundException(String title) {
        super("Book with title: " + title + " not found");
    }
}
