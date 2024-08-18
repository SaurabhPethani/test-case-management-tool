package com.example.testcasemanagement.controller;

import com.example.testcasemanagement.dto.UserDTO;
import com.example.testcasemanagement.model.User;
import com.example.testcasemanagement.service.UserService;
import com.example.testcasemanagement.util.ApiResponse;
import com.example.testcasemanagement.util.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${allowed.users}")
    private String allowedUsers;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> authenticateUser(@RequestBody @Valid UserDTO userDTO) {
        String username = userDTO.getUsername();

        if(!allowedUsers.contains(username)) {
            // return unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(null, "User is not present in configured user list"));
        }
        User user = userService.findByUsername(username);
        if(user != null) {
//            String encode = passwordEncoder.encode(userDTO.getPassword());
//            if (user.getPassword().equals(encode))
            String encoded = passwordEncoder.encode("1234");
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                if (user.getRole().contains("ADMIN") || user.getRole().contains("TESTER")) {

                    String token = jwtUtils.generateToken(username, List.of(user.getRole()));
                    return ResponseEntity.ok(new ApiResponse<>(token, "found"));
                } else {
                    // return unauthorized
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(new ApiResponse<>(null, "User is neither tester nor admin"));
                }
            } else {
                // return Invalid password
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "Invalid Credentials"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(null, "User not found"));
        }
    }

}
