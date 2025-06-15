package com.example.libraryapifinalproject.repository;
import com.example.libraryapifinalproject.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}