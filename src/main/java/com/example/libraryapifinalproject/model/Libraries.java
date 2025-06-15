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
public class Libraries extends User {
    private String address;


    @OneToMany(mappedBy = "libraries", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference("libraries-books")
    private List<Book> books = new ArrayList<>();

    public Libraries(String username, String password, String address) {
        super(username, password);
        this.address = address;
    }

}
