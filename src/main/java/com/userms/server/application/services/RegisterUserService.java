package com.userms.server.application.services;

import com.userms.server.application.ports.input.RegisterUserUseCase;
import com.userms.server.application.ports.output.UserRepository;
import com.userms.server.domain.exception.UserAlreadyExistsException;
import com.userms.server.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public User registerUser(String username, String password, String email, String fullName) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("El nombre de usuario ya está en uso");
        }
        
        // Verificar si el email ya existe
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("El correo electrónico ya está en uso");
        }
        
        // Crear el nuevo usuario
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        User newUser = new User(
            null, // ID será generado por la base de datos
            username,
            passwordEncoder.encode(password), // Encriptar la contraseña
            email,
            fullName,
            true, // Habilitado por defecto
            now,  // Fecha de creación
            now   // Fecha de actualización
        );
        
        // Guardar y retornar el usuario
        return userRepository.save(newUser);
    }
} 