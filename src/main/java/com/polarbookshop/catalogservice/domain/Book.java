package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Book(

        @Id
        Long id,

        @NotBlank(message = "ISBN must not be blank.")
        String isbn,

        @NotBlank(message = "Title must be defined.")
        String title,

        @NotBlank(message = "Author must be defined.")
        String author,

        @NotNull(message = "Price must be defined.")
        @Positive(message = "Price must be positive.")
        double price,

        @Version
        int version
) {
        public static Book of(
                String isbn,
                String title,
                String author,
                double price
        ) {
                return new Book(null, isbn, title, author, price, 0);
        }
}
