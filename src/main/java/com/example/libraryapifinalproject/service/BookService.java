package com.example.libraryapifinalproject.service;

import com.example.libraryapifinalproject.model.Book;
import com.example.libraryapifinalproject.model.Libraries;
import com.example.libraryapifinalproject.repository.BookRepository;
import com.example.libraryapifinalproject.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LibraryRepository libraryRepository;

    public BookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }

    public Book addBook(Long libraryId, Book book) {
        Libraries libraries = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with ID: " + libraryId));

        book.setLibraries(libraries);
        return bookRepository.save(book);
    }

    public void removeBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));


        Libraries libraries = book.getLibraries();
        if (libraries != null) {
            libraries.getBooks().remove(book);
        }

        bookRepository.delete(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}