package com.example.swp.service;

import com.example.swp.dto.ConsultationDTO;
import com.example.swp.entity.Consultation;

import java.time.LocalDate;
import java.util.List;

public interface ConsultationService {
    ConsultationDTO createConsultation(Integer userId, Integer coachId, LocalDate date);

    ConsultationDTO getConsultationById(Integer consultationId);

    List<ConsultationDTO> getConsultationsByUserId(Integer userId);
}
