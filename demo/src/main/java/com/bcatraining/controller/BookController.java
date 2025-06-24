package com.bcatraining.controller;

import com.bcatraining.helper.dto.ApiResponse;
import com.bcatraining.helper.dto.request.BookRequest;
import com.bcatraining.helper.handler.RequestNotValidException;
import com.bcatraining.helper.dto.response.BookResponse;
import com.bcatraining.model.Book;
import com.bcatraining.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<List<BookResponse>> getAllBooks() {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.getAllBook();
    }

    @GetMapping(path="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<BookResponse> getBookById(@PathVariable Long id) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.getBookById(id);
    }

    @GetMapping(path="/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<BookResponse> getBookByTitle(@PathVariable String title) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.getByTitle(title);
    }

    @GetMapping(path="/author/{author}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<List<BookResponse>> getBookByAuthor(@PathVariable String author) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.getBooksByAuthor(author);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<BookResponse> createBook(@Valid @RequestBody BookRequest book) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.insertBook(book);
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<BookResponse> deleteBookById(@PathVariable Long id) {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.deleteBookById(id);
    }

    @PutMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest book) throws RequestNotValidException {
        String sourceId = request.getHeader("X-Request-Source");
        String traceId = request.getHeader("X-Request-Trace");
        logger.info("Request Trace ID: {}", traceId);
        logger.info("Request Source ID: {}", sourceId);
        return bookService.updateBook(id, book);
    }

}
