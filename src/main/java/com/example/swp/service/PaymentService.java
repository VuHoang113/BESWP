package com.example.swp.service;

import com.example.swp.dto.request.PaymentDTO;

public interface PaymentService {

    PaymentDTO createPayment(Integer userId, Integer packageId);
}
