package com.example.testcasemanagement.util;

import org.springframework.security.core.userdetails.UserDetails;

public class Utilities {

    public static boolean isAdmin(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"));
    }
}
