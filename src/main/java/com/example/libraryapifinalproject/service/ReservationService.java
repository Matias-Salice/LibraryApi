package com.example.libraryapifinalproject.service;

import com.example.libraryapifinalproject.model.Book;
import com.example.libraryapifinalproject.model.Customer;
import com.example.libraryapifinalproject.model.Reservation;
import com.example.libraryapifinalproject.model.ReservationStatus;
import com.example.libraryapifinalproject.repository.BookRepository;
import com.example.libraryapifinalproject.repository.CustomerRepository;
import com.example.libraryapifinalproject.repository.ReservationRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              BookRepository bookRepository,
                              CustomerRepository customerRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public Reservation createReservation(Long bookId, Long customerId, String date, String time) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }

        Book book = optionalBook.get();
        Customer customer = optionalCustomer.get();


        if (book.getCopiesAvailable() <= 0) {
            throw new RuntimeException("No copies available for book: " + book.getTitle());
        }


        book.setCopiesAvailable(book.getCopiesAvailable() - 1);

        Reservation reservation = new Reservation();
        reservation.setBook(book);
        reservation.setCustomer(customer);
        reservation.setDate(date);
        reservation.setTime(time);
        reservation.setStatus(ReservationStatus.CONFIRMED);

        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Long reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isEmpty()) {
            throw new RuntimeException("Reservation not found with ID: " + reservationId);
        }

        Reservation reservation = optionalReservation.get();

        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new RuntimeException("Reservation is already cancelled.");
        }


        reservation.setStatus(ReservationStatus.CANCELLED);

        Book book = reservation.getBook();
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);

        return reservationRepository.save(reservation);
    }
}