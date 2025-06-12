package com.example.swp.dto;


import com.example.swp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private Double amount;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}

