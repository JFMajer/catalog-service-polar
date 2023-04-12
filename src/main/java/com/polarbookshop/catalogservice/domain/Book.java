package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Book(

        @NotBlank(message = "ISBN must not be blank.")
        String isbn,

        @NotBlank(message = "Title must be defined.")
        String title,

        @NotBlank(message = "Author must be defined.")
        String author,

        @NotNull(message = "Price must be defined.")
        @Positive(message = "Price must be positive.")
        double price
) {
}
