package com.example.swp.service;

import com.example.swp.dto.request.PaymentDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(Integer userId, Integer packageId);

    List<com.example.swp.dto.PaymentDTO> getPaymentByUserId(Integer userId);
}
