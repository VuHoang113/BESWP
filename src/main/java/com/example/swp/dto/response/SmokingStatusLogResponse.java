package com.example.swp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmokingStatusLogResponse {
    private Integer logID;
    private Integer userId;
    private LocalDate logDate;
    private Integer cigarettesPerDay;
    private String smokingFrequency;
    private Double costPerPack;
    private String notes;
}