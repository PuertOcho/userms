package com.userms.server.application.ports.input;

import com.userms.server.domain.model.User;

public interface LoginUseCase {
    User login(String email, String password);
} 