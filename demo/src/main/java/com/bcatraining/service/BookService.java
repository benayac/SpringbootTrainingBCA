package com.bcatraining.service;

import com.bcatraining.handler.BookNotFoundException;
import com.bcatraining.model.entity.Book;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookService {
    private final HashMap<Long, Book> bookList = new HashMap<>();

    public List<Book> findAll() {
        return List.copyOf(this.bookList.values());
    }

    public Book findById(Long id) {
        Book book = this.bookList.get(id);
        if(book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public Book save(Book book) {
        this.bookList.put(book.getBookId(), book);
        return book;
    }

    public Book update(Long id, Book book) {
        this.bookList.put(id, book);
        return book;
    }

    public Book delete(Long id) {
        return this.bookList.remove(id);
    }
}
