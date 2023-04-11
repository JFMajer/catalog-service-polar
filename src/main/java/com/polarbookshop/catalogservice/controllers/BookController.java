package com.polarbookshop.catalogservice.controllers;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // get all the books in the catalog
    @GetMapping("/books")
    public Iterable getAllBooks() {
        return bookService.viewBookList();
    }

    // get one book by isbn
    @GetMapping("/books/{isbn}")
    public Book getBookByIsbn(String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    // add new book to catalog
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book) {
        return bookService.addBookToCatalog(book);
    }

    // delete book from catalog
    @DeleteMapping("/books/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("/books/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book book) {
        return bookService.updateBookDetails(isbn, book);
    }


}
