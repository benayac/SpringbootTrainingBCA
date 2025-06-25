package com.bcatraining.helper.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public record BookRequest (
    @NotBlank(message = "title is required")
    String title,
    @Range(min=1900, max=2500, message = "book year is outside range")
    Integer publishedYear,
    @NotBlank(message = "author is required")
    String author,
    @NotBlank(message = "category must not be empty")
    String category
) {

}
