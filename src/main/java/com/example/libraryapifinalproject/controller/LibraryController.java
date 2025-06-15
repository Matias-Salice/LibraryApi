package com.example.libraryapifinalproject.controller;


import com.example.libraryapifinalproject.dto.LibraryRequestDTO;
import com.example.libraryapifinalproject.dto.ReservationRequestDTO;
import com.example.libraryapifinalproject.model.Book;
import com.example.libraryapifinalproject.model.Libraries;
import com.example.libraryapifinalproject.model.Reservation;
import com.example.libraryapifinalproject.service.BookService;
import com.example.libraryapifinalproject.service.CustomerService;
import com.example.libraryapifinalproject.service.LibraryService;
import com.example.libraryapifinalproject.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService libraryService;
    private final BookService bookService;
    private final ReservationService reservationService;
    private final CustomerService customerService;

    public LibraryController(LibraryService libraryService, BookService bookService,
                             ReservationService reservationService, CustomerService customerService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
        this.reservationService = reservationService;
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Libraries> createLibrary(@RequestBody LibraryRequestDTO request) {
        Libraries libraries = new Libraries(request.getUsername(), request.getPassword(), request.getAddress());
        Libraries saved = libraryService.createLibrary(libraries);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getBooksByLibrary(@PathVariable Long id) {
        List<Book> books = libraryService.getBooksByLibrary(id);
        return ResponseEntity.ok(books);
    }


    @PostMapping("/{libraryId}/books/{bookId}/reserve")
    public ResponseEntity<Reservation> reserveBook(@PathVariable Long libraryId,
                                                   @PathVariable Long bookId,
                                                   @RequestBody ReservationRequestDTO request) {
        Reservation reservation = reservationService.createReservation(
                bookId,
                request.getCustomerId(),
                request.getDate(),
                request.getTime()
        );
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @PutMapping("/reservations/{reservationId}/cancel")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long reservationId) {
        Reservation cancelled = reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok(cancelled);
    }
}