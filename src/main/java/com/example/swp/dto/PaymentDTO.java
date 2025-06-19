package com.example.swp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentDTO {
    private Integer paymentId;
    private String userName;
    private Double amount;
    private String paymentMethod;
    private String status;
    private String packageName;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;
}
