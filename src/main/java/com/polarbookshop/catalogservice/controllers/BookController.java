package com.polarbookshop.catalogservice.controllers;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // get all the books in the catalog
    @GetMapping("/books")
    public Iterable getAllBooks() {
        log.info("Fetching the list of all books in the catalog.");
        return bookService.viewBookList();
    }

    // get one book by isbn
    @GetMapping("/books/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    // add new book to catalog
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    // add multiple books to catalog
    @PostMapping("/books/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Book> addBooks(@Valid @RequestBody Iterable<Book> books) {
        for (Book book : books) {
            bookService.addBookToCatalog(book);
        }
        return books;
    }

    // delete book from catalog
    @DeleteMapping("/books/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    // update book details
    @PutMapping("/books/{isbn}")
    public Book updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.updateBookDetails(isbn, book);
    }


}
