package com.example.libraryapifinalproject.repository;

import com.example.libraryapifinalproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}