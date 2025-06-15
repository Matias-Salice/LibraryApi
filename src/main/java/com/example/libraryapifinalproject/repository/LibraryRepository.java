package com.example.libraryapifinalproject.repository;

import com.example.libraryapifinalproject.model.Libraries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Libraries, Long> {
}