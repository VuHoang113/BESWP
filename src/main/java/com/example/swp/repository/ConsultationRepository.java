package com.example.swp.repository;

import com.example.swp.dto.ConsultationDTO;
import com.example.swp.entity.Consultation;
import com.example.swp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    @Query("SELECT c FROM Consultation c WHERE c.user.userID = :userId AND c.status = 'PENDING' AND c.scheduledTime = CURRENT_DATE")
    Consultation findByInvalid(Integer userId);

    Optional<List<Consultation>> findByUser(User user);


}

