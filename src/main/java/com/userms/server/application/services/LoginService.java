package com.userms.server.application.services;

import com.userms.server.application.ports.input.LoginUseCase;
import com.userms.server.application.ports.output.UserRepository;
import com.userms.server.domain.model.User;
import com.userms.server.domain.exception.UnauthorizedException;
import com.userms.server.domain.exception.ErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
            .map(user -> {
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    throw new UnauthorizedException(ErrorCode.INVALID_PASSWORD);
                }
                return user;
            })
            .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_FOUND));
    }
} 