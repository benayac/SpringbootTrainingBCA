package com.bcatraining.controller;

import com.bcatraining.handler.RequestNotValidException;
import com.bcatraining.model.BookRequest;
import com.bcatraining.model.BookResponse;
import com.bcatraining.model.entity.Book;
import com.bcatraining.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse<List<Book>> getAllBooks() {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        BookResponse<List<Book>> bookResponse = new BookResponse<>();
        bookResponse.setData(bookService.findAll());
        bookResponse.setMessage("success");
        return bookResponse;
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse<Book> getBookById(@PathVariable Long id) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        BookResponse<Book> bookResponse = new BookResponse<>();
        bookResponse.setMessage("success");
        bookResponse.setData(bookService.findById(id));
        return bookResponse;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse<Book> createBook(@Valid @RequestBody Book book) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        BookResponse<Book> bookResponse = new BookResponse<>();
        bookResponse.setMessage("success");
        bookResponse.setData(bookService.save(book));
        return bookResponse;
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse<Book> deleteBookById(@PathVariable Long id) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        BookResponse<Book> bookResponse = new BookResponse<>();
        bookResponse.setData(bookService.delete(id));
        bookResponse.setMessage("Book with ID " + id + " has been deleted.");
        return bookResponse;
    }

    @PutMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponse<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) throws RequestNotValidException {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        BookResponse<Book> bookResponse = new BookResponse<>();
        bookResponse.setMessage("Book with ID " + id + " has been updated.");
        bookResponse.setData(bookService.update(id, book));
        return bookResponse;
    }

}
