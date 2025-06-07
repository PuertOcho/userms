package com.userms.server.application.ports.input;

import com.userms.server.domain.model.User;

public interface RegisterUserUseCase {
    User registerUser(String username, String password, String email, String fullName);
} 