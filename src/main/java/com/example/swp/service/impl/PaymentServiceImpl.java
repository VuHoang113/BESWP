package com.example.swp.service.impl;

import com.example.swp.dto.request.PaymentDTO;
import com.example.swp.entity.MembershipPackage;
import com.example.swp.entity.Payment;
import com.example.swp.entity.User;
import com.example.swp.mapper.PaymentMapper;
import com.example.swp.repository.MembershipPackageRepository;
import com.example.swp.repository.PaymentRepository;
import com.example.swp.repository.UserRepository;
import com.example.swp.service.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    @org.springframework.transaction.annotation.Transactional
    public PaymentDTO createPayment(Integer userId, Integer packageId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPackage membershipPackage = membershipPackageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Membership Package not found"));

        if(user.getAmount()< membershipPackage.getPrice()) {
            throw new RuntimeException("Insufficient balance");
        }
        if(user.getSubscriptionEndDate().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("User already has an active membership");
        }

        user.setAmount(user.getAmount() - membershipPackage.getPrice());
        user.setRole("CUSTOMER");
        userRepository.save(user);

        Payment payment = new Payment();

        if(payment.getUser() != null) {
            throw new RuntimeException("User already has an active payment");
        }
        if(payment.getExpirationDate() == null || payment.getExpirationDate().isAfter(java.time.LocalDateTime.now())) {
            payment.setExpirationDate(java.time.LocalDateTime.now().plusDays(membershipPackage.getDurationDays()));
        }
        payment.setExpirationDate(java.time.LocalDateTime.now().plusDays(membershipPackage.getDurationDays()));
        payment.setUser(user);
        payment.setPackageInfo(membershipPackage);
        payment.setAmount(membershipPackage.getPrice());
        payment.setPaymentDate(java.time.LocalDateTime.now());
        paymentRepository.save(payment);
        return new PaymentDTO( user.getFullName(), membershipPackage.getPackageName(),payment.getPaymentDate(),
                payment.getAmount(),"Successful", payment.getExpirationDate());
    }

    @Override
    public List<com.example.swp.dto.PaymentDTO> getPaymentByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<com.example.swp.dto.PaymentDTO> payment = paymentRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Payment not found for user"))
                .stream()
                .map(PaymentMapper::toDTO)
                .toList();
        return payment;
    }
}
