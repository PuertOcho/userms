package com.userms.server.infrastructure.adapters.input.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private String message;
} 