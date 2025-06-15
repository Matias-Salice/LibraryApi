package com.example.libraryapifinalproject.repository;

import com.example.libraryapifinalproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}