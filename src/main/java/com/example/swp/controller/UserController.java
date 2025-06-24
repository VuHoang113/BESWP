package com.example.swp.controller;

import com.example.swp.dto.ConsultationDTO;
import com.example.swp.dto.UserDTO;
import com.example.swp.mapper.UserMapper;
import com.example.swp.dto.request.PaymentDTO;
import com.example.swp.service.ConsultationService;
import com.example.swp.service.PaymentService;
import com.example.swp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ConsultationService consultationService;


    @GetMapping("/profile")
    public UserDTO getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return UserMapper.toDTO(userService.updateUser(id, UserMapper.toEntity(userDTO)));
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }


    //nap tien vao vi
    @PostMapping("/topUpWallet/{id}")
    public UserDTO topUpWallet(@PathVariable Integer id, @RequestParam double amount) {
        return userService.topupBalance(id, amount);
    }

    // Đặt gói thành viên
    @PostMapping("/booking")
    public PaymentDTO bookMembershipPackage(@RequestParam Integer userId, @RequestParam Integer packageId) {
        // Tạo thanh toán cho gói thành viên
      return  paymentService.createPayment(userId, packageId);
    }

    @GetMapping("/payment/{id}")
    public List<com.example.swp.dto.PaymentDTO> getPaymentById(@PathVariable Integer id) {
        return paymentService.getPaymentByUserId(id);
    }
    @PostMapping("/consultation/{userId}/{coachId}")
    public ConsultationDTO createConsultion (@PathVariable Integer userId,@PathVariable Integer coachId, @RequestParam LocalDate dateTime) {
        return consultationService.createConsultation(userId,coachId,dateTime);
    }
    @GetMapping("/consultation")
    public ConsultationDTO getConsultationById(@RequestParam Integer consultationId) {
        return consultationService.getConsultationById(consultationId);
    }

    @GetMapping("/consultations/{userId}")
    public List<ConsultationDTO> getConsultationsByUserId(@PathVariable Integer userId) {
        return consultationService.getConsultationsByUserId(userId);
    }
}
