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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("success");
        bookResponse.setData(bookService.findById(id));
        return bookResponse;
    }

    @PostMapping
    public BookResponse createBook(@Valid @RequestBody Book book) {
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("success");
        bookResponse.setData(bookService.save(book));
        return bookResponse;
    }

    @DeleteMapping("/{id}")
    public BookResponse deleteBookById(@PathVariable Long id) {
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        bookService.delete(id);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("Book with ID " + id + " has been deleted.");
        return bookResponse;
    }

    @PutMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @Valid @RequestBody Book book) throws RequestNotValidException {
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        bookService.update(id, book);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("Book with ID " + id + " has been updated.");
        return bookResponse;
    }

}
