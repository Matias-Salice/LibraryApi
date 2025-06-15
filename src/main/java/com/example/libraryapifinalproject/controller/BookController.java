package com.example.libraryapifinalproject.controller;

import com.example.libraryapifinalproject.model.Book;
import com.example.libraryapifinalproject.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/library/{libraryId}")
    public ResponseEntity<Book> addBook(@PathVariable Long libraryId, @RequestBody Book book) {
        Book savedBook = bookService.addBook(libraryId, book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable Long bookId) {
        bookService.removeBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}