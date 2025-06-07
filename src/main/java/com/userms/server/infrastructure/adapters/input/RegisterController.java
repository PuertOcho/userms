package com.userms.server.infrastructure.adapters.input;

import com.userms.server.application.ports.input.RegisterUserUseCase;
import com.userms.server.domain.exception.UserAlreadyExistsException;
import com.userms.server.domain.model.User;
import com.userms.server.infrastructure.adapters.input.dto.RegisterRequest;
import com.userms.server.infrastructure.adapters.input.dto.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            User user = registerUserUseCase.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getFullName()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getFullName(),
                    "Usuario registrado exitosamente"
                )
            );
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al registrar el usuario");
        }
    }
} 