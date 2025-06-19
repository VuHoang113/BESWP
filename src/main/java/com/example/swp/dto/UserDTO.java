package com.example.swp.dto;


import com.example.swp.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private Double amount;
    private String status;
    private String url;
    private String role;
    private LocalDateTime lastLogin;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}

