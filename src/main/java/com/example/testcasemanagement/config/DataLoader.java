package com.example.testcasemanagement.config;

import com.example.testcasemanagement.model.User;
import com.example.testcasemanagement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadInitialData() {
        if (userRepository.count() == 0) {
//            User admin = new User("admin", passwordEncoder.encode("admin123"), "ROLE_ADMIN");
//            User tester = new User("saurabh", passwordEncoder.encode("tester123"), "ROLE_TESTER");
//            userRepository.saveAll(Arrays.asList(admin, tester));
        }
    }
}
