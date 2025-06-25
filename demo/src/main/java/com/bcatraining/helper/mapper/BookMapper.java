package com.bcatraining.helper.mapper;

import com.bcatraining.helper.dto.request.BookRequest;
import com.bcatraining.helper.dto.response.BookResponse;
import com.bcatraining.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper implements ResponseMapper<Book, BookRequest, BookResponse> {

    @Override
    public Book requestToModel(BookRequest request) {
        return Book.builder()
                .title(request.title())
                .publishedYear(request.publishedYear())
                .build();
    }

    @Override
    public BookResponse modelToResponse(Book model) {
        return new BookResponse(
                model.getId(),
                model.getTitle(),
                model.getAuthor().getName(),
                model.getPublishedYear(),
                model.getCategory().getCategory()
        );
    }

    @Override
    public List<BookResponse> listModelToListResponse(List<Book> models) {
        if(!models.isEmpty()) {
            return models.stream()
                    .map(this::modelToResponse)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
