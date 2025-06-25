package com.bcatraining.helper.dto.response;

public record BookResponse(
        Long id,
        String title,
        String author,
        Integer publishedYear,
        String category
){
}
