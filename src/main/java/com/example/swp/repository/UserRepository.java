package com.example.swp.repository;

import com.example.swp.entity.Coach;
import com.example.swp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    List<User> findByCoach(Coach coach);
}

