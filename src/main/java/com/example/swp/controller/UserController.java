package com.example.swp.controller;

import com.example.swp.dto.UserDTO;
import com.example.swp.dto.UserMapper;
import com.example.swp.dto.request.PaymentDTO;
import com.example.swp.service.PaymentService;
import com.example.swp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;


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
      return  paymentService.createPayment(userId, packageId);
    }
}
