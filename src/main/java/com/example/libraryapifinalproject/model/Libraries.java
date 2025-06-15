package com.example.libraryapifinalproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Library extends User {
    private String address;


    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Library(String username, String password, String address) {
        super(username, password);
        this.address = address;
    }


    public void addBook(Book book) {
        books.add(book);
        book.setLibrary(this);
    }


    public void removeBook(Book book) {
        books.remove(book);
        book.setLibrary(null);
    }
}
