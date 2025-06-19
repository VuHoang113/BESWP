package com.example.swp.util;

import com.example.swp.entity.User;
import com.example.swp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataConfigUtil implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        if(userRepository.findByUsername("admin").isPresent()){
            return;
        }
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@gmail.com");
        user.setRole("ADMIN");
        user.setPasswordHash(passwordEncoder.encode("admin"));
        user.setVerified(true);
        userRepository.save(user);
    }
}
