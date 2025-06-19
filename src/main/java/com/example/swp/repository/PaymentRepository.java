package com.example.swp.repository;

import com.example.swp.entity.Payment;
import com.example.swp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<List<Payment>> findByUser(User user);
}

