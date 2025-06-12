package com.example.swp.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRegisterRequest {
    private String username;
    private String email;
    private String password;
    private String fullName;
}

