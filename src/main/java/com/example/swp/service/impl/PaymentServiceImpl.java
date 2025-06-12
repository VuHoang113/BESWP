package com.example.swp.service.impl;

import com.example.swp.dto.request.PaymentDTO;
import com.example.swp.entity.MembershipPackage;
import com.example.swp.entity.Payment;
import com.example.swp.entity.User;
import com.example.swp.repository.MembershipPackageRepository;
import com.example.swp.repository.PaymentRepository;
import com.example.swp.repository.UserRepository;
import com.example.swp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipPackageRepository membershipPackageRepository;


    @Override
    public PaymentDTO createPayment(Integer userId, Integer packageId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPackage membershipPackage = membershipPackageRepository.findById(packageId)
                .orElseThrow(() -> new RuntimeException("Membership Package not found"));

        if(user.getAmount()< membershipPackage.getPrice()) {
            throw new RuntimeException("Insufficient balance");
        }
        user.setAmount(user.getAmount() - membershipPackage.getPrice());
        user.setRole("USER");
        user.setCurrentMembershipPackage(membershipPackage);
        userRepository.save(user);

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPackageInfo(membershipPackage);
        payment.setAmount(membershipPackage.getPrice());
        payment.setPaymentDate(java.time.LocalDateTime.now());
        paymentRepository.save(payment);
        return new PaymentDTO( user.getFullName(), membershipPackage.getPackageName(),payment.getPaymentDate(),
                payment.getAmount(),"Successful");
    }
}
