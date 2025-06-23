package com.bcatraining.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
//    @NotNull(message = "ID Could Not Be Empty")
    private Long bookId;
    @NotBlank(message = "Book Title Could Not Be Empty")
    private String bookName;
    @NotBlank(message = "Book Author Could Not Be Empty")
    private String bookAuthor;
    private String bookSummary;

//    public Long getBookId() {
//        return bookId;
//    }
//
//    public void setBookId(Long bookId) {
//        this.bookId = bookId;
//    }
//
//    public String getBookName() {
//        return bookName;
//    }
//
//    public void setBookName(String bookName) {
//        this.bookName = bookName;
//    }
//
//    public String getBookAuthor() {
//        return bookAuthor;
//    }
//
//    public void setBookAuthor(String bookAuthor) {
//        this.bookAuthor = bookAuthor;
//    }
//
//    public String getBookSummary() {
//        return bookSummary;
//    }
//
//    public void setBookSummary(String bookSummary) {
//        this.bookSummary = bookSummary;
//    }
}
