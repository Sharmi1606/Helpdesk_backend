package com.springboot.helpdesk_backend.repository;

import com.springboot.helpdesk_backend.model.Customer;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("""
            select c from Customer c
            JOIN c.user u
            where u.username=?1
            """)
    Customer getByUsername(String customerUsername);

    Optional<Object> findByUserUsername(@NotBlank(message = "Username is required") String username);
}