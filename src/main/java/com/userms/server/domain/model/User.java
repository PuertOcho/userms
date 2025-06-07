package com.userms.server.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private boolean enabled;
    private String createdAt;
    private String updatedAt;
    
    // Constructor, getters y setters
} 