package com.example.libraryapifinalproject.service;

import com.example.libraryapifinalproject.model.Book;
import com.example.libraryapifinalproject.model.Libraries;
import com.example.libraryapifinalproject.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Libraries createLibrary(Libraries libraries) {
        return libraryRepository.save(libraries);
    }

    public Libraries getLibraryById(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + id));
    }

    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }

    public List<Book> getBooksByLibrary(Long id) {
        Libraries libraries = getLibraryById(id);
        return libraries.getBooks();
    }
}