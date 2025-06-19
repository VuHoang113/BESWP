package com.example.swp.service.impl;

import com.example.swp.dto.ConsultationDTO;
import com.example.swp.entity.Consultation;
import com.example.swp.entity.User;
import com.example.swp.repository.CoachRepository;
import com.example.swp.repository.ConsultationRepository;
import com.example.swp.repository.UserRepository;
import com.example.swp.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsultationImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoachRepository coachRepository;



    @Override
    public ConsultationDTO createConsultation(Integer userId, Integer coachId , LocalDate date) {
        if(userId == null || coachId == null || date == null) {
            throw new IllegalArgumentException("User ID, Coach ID, and date must not be null");
        }
        if (consultationRepository.findByInvalid(userId) != null) {
            throw new IllegalArgumentException("User already has an invalid consultation in day");
        }
        if(date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Scheduled date must be today or in the future");
        }
            Consultation consultation = new Consultation();
            consultation.setUser(userRepository.findById(userId).orElse(null));
            consultation.setCoach(coachRepository.findById(coachId).orElse(null));
            consultation.setStatus("PENDING");
            consultation.setMeetingLink("PENDING COACH");
            consultation.setScheduledTime(date);
            consultation.setNotes("PENDING");
        return ConsultationDTO.toDTO(consultationRepository.save(consultation));
    }

    @Override
    public ConsultationDTO getConsultationById(Integer consultationId) {
        return consultationRepository.findById(consultationId)
                .map(ConsultationDTO::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Consultation not found with ID: " + consultationId));
    }

    @Override
    public List<ConsultationDTO> getConsultationsByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        List<Consultation> consultations = consultationRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("No consultations found for user with ID: " + userId)
        );
        return consultations.stream()
                .map(ConsultationDTO::toDTO)
                .toList();
    }
}

