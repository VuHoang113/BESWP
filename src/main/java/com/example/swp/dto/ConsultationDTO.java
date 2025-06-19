package com.example.swp.dto;

import com.example.swp.entity.Consultation;
import lombok.Data;

@Data
public class ConsultationDTO {
    private Integer id;
    private String userName;
    private String coachName;
    private String status;
    private String note;
    private String meetingLink;

    public static ConsultationDTO toDTO(Consultation consultation){
        if (consultation == null) {
            return null;
        }
        ConsultationDTO dto = new ConsultationDTO();
        dto.setId(consultation.getConsultationID());
        dto.setUserName(consultation.getUser() != null ? consultation.getUser().getUsername() : null);
        dto.setCoachName(consultation.getCoach() != null ? consultation.getCoach().getFullName() : null);
        dto.setStatus(consultation.getStatus());
        dto.setNote(consultation.getNotes());
        dto.setMeetingLink(consultation.getMeetingLink());
        return dto;

    }

}
