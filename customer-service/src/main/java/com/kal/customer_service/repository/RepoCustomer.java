package com.kal.customer_service.repository;

import com.kal.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoCustomer extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
