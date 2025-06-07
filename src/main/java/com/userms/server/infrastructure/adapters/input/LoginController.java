package com.userms.server.infrastructure.adapters.input;

import com.userms.server.application.ports.input.LoginUseCase;
import com.userms.server.domain.model.User;
import com.userms.server.infrastructure.adapters.input.dto.LoginRequest;
import com.userms.server.infrastructure.adapters.input.dto.LoginResponse;
import com.userms.server.infrastructure.adapters.input.dto.ErrorResponse;
import com.userms.server.infrastructure.security.JwtTokenProvider;
import com.userms.server.domain.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {
    private final LoginUseCase loginUseCase;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = loginUseCase.login(request.getEmail(), request.getPassword());
            String token = jwtTokenProvider.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(
                token,
                "Bearer",
                user.getUsername(),
                user.getEmail(),
                user.getFullName()
            ));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getErrorCode().getCode(), e.getMessage()));
        }
    }
} 