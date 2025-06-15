package service;

import model.Book;
import model.Library;
import repository.BookRepository;
import repository.LibraryRepository;
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
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with ID: " + libraryId));

        book.setLibrary(library);
        return bookRepository.save(book);
    }

    public void removeBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));


        Library library = book.getLibrary();
        if (library != null) {
            library.getBooks().remove(book);
        }

        bookRepository.delete(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}