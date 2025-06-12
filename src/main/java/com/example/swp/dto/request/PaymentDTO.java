package com.example.swp.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PaymentDTO {

    private String userName;
    private String packageName;
    private LocalDateTime paymentDate;
    private Double amount;
    private String status;


}
