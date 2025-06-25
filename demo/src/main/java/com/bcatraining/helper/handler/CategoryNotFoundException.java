package com.bcatraining.helper.handler;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String title) {
        super("Category with title: " + title + " not found");
    }
}
