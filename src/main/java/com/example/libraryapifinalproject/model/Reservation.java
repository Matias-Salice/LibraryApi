package com.example.libraryapifinalproject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String time;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @com.fasterxml.jackson.annotation.JsonBackReference("book-reservations")
    private Book book;

    @ManyToOne
    @com.fasterxml.jackson.annotation.JsonBackReference("customer-reservations")
    private Customer customer;


}