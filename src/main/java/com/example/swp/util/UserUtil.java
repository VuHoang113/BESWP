package com.example.swp.util;

import com.example.swp.entity.User;
import com.example.swp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    @Autowired
    UserRepository userRepository;

    public User getCurrentUser(){
        String userName=  SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(userName).orElse(null);
    }
}
