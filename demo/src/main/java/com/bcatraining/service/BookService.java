package com.bcatraining.service;

import com.bcatraining.helper.dto.ApiResponse;
import com.bcatraining.helper.dto.request.BookRequest;
import com.bcatraining.helper.dto.response.BookResponse;
import com.bcatraining.helper.handler.BookNotFoundException;
import com.bcatraining.helper.handler.CategoryNotFoundException;
import com.bcatraining.helper.handler.CustomException;
import com.bcatraining.helper.mapper.BookMapper;
import com.bcatraining.model.Author;
import com.bcatraining.model.Book;
import com.bcatraining.model.Category;
import com.bcatraining.repository.AuthorRepository;
import com.bcatraining.repository.BookRepository;
import com.bcatraining.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final BookMapper bookMapper;

    public BookService(BookRepository repo, CategoryRepository categoryRepository, AuthorRepository authorRepository, BookMapper bookMapper) {
        this.bookRepository = repo;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    public ApiResponse<List<BookResponse>> getAllBook() {
        return bookMapper.mapListModelToListResponse(bookRepository.findAll());
    }

    public ApiResponse<BookResponse> getBookById(long id) {
        if(!bookRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Book Id Not Found",
                    "Book Not Found");
        }
        return bookMapper.mapToApiResponse(bookRepository.findById(id).get());
    }

    public ApiResponse<BookResponse> insertBook(BookRequest book) {
        Book bookEntity = bookMapper.requestToModel(book);
        if(!categoryRepository.findByCategory(book.category()).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Category Not Found",
                    "Category Not Found");
        }
        Category category = categoryRepository.findByCategory(book.category()).get();
        bookEntity.setCategory(category);
        if(!authorRepository.findByNameIgnoreCase(book.author()).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Author Not Found",
                    "Author Not Found");
        }
        Author author = authorRepository.findByNameIgnoreCase(book.author()).get();
        bookEntity.setAuthor(author);
        return bookMapper.mapToApiResponse(bookRepository.save(bookEntity));
    }

    @Transactional
    public ApiResponse<BookResponse>  updateBook(Long id, BookRequest book) {
        if(!bookRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Book Id Not Found",
                    "Book Not Found");
        }
        Book bookOld = bookRepository.findById(id).get();
        bookOld.setTitle(book.title());
        bookOld.setPublishedYear(book.publishedYear());
        bookOld = bookRepository.save(bookOld);
        if(bookOld.getCategory().getCategory() != book.category()) {
            if (!categoryRepository.findByCategory(book.category()).isPresent()) {
                throw new CustomException(HttpStatus.NOT_FOUND,
                        "Category Not Found",
                        "Category Not Found");
            }
            Category category = categoryRepository.findByCategory(book.category()).get();
            bookOld.setCategory(category);
            bookOld = bookRepository.save(bookOld);
        }
        if(bookOld.getAuthor().getName() != book.author()) {
            if(!authorRepository.findByNameIgnoreCase(book.author()).isPresent()) {
                throw new CustomException(HttpStatus.NOT_FOUND,
                        "Author Not Found",
                        "Author Not Found");
            }
            Author author = authorRepository.findByNameIgnoreCase(book.author()).get();
            bookOld.setAuthor(author);
            bookOld = bookRepository.save(bookOld);
        }
        return bookMapper.mapToApiResponse(bookOld);
    }

    public ApiResponse<BookResponse> deleteBookById(long id) {
        if(!bookRepository.findById(id).isPresent()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Book Id Not Found",
                    "Book Not Found");
        }
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
        return bookMapper.mapToApiResponse(book);
    }

    public ApiResponse<BookResponse> getByTitle(String title) {
        if(bookRepository.findByTitle(title).isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Book Title Not Found",
                    "Book Not Found");
        }
        return bookMapper.mapToApiResponse(bookRepository.findByTitle(title).get());
    }

    public ApiResponse<List<BookResponse>> getBooksByAuthor(String author) {
        if(authorRepository.findByNameIgnoreCase(author).isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND,
                    "Book Title Not Found",
                    "Book Not Found");
        }
        Author authorEntity = authorRepository.findByNameIgnoreCase(author).get();
        List<Book> bookList = authorEntity.getBooks();
        return bookMapper.mapListModelToListResponse(bookList);
    }
}
