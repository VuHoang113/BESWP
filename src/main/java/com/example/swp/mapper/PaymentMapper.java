package com.example.swp.mapper;

import com.example.swp.dto.PaymentDTO;
import com.example.swp.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(payment.getPaymentID());
        dto.setUserName(payment.getUser().getUsername());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setCreatedDate(payment.getPaymentDate());
        dto.setPackageName(payment.getPackageInfo().getPackageName());
        dto.setExpirationDate(payment.getUser().getSubscriptionEndDate());
        dto.setPaymentMethod("PAY WALLET");
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        if (dto == null) return null;
        Payment payment = new Payment();
        payment.setPaymentID(dto.getPaymentId());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        payment.setPaymentDate(dto.getCreatedDate());
        payment.setExpirationDate(dto.getExpirationDate());
        payment.setPaymentMethod("PAY WALLET");
        return payment;
    }
}