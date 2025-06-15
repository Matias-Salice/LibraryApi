package com.example.libraryapifinalproject.service;
import com.example.libraryapifinalproject.model.Customer;
import com.example.libraryapifinalproject.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Crear nuevo cliente
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Obtener un cliente por su ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Obtener todos los clientes
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Eliminar cliente
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}