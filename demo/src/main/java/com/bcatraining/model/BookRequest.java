package com.bcatraining.model;

import com.bcatraining.model.entity.Book;
import jakarta.validation.constraints.NotNull;

public class BookRequest {
    @NotNull
    private Book data;

    public @NotNull Book getData() {
        return data;
    }

    public void setData(@NotNull Book data) {
        this.data = data;
    }
}
