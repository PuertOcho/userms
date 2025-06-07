package com.userms.server.infrastructure.mapper;

import com.userms.server.domain.model.User;
import com.userms.server.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public User toModel(UserEntity entity) {
        if (entity == null) return null;
        return new User(
            entity.getId(),
            entity.getUsername(),
            entity.getPassword(),
            entity.getEmail(),
            entity.getFullName(),
            entity.isEnabled(),
            entity.getCreatedAt().format(DATE_FORMATTER),
            entity.getUpdatedAt().format(DATE_FORMATTER)
        );
    }

    public UserEntity toEntity(User model) {
        if (model == null) return null;
        return new UserEntity(
            model.getId(),
            model.getUsername(),
            model.getPassword(),
            model.getEmail(),
            model.getFullName(),
            model.isEnabled(),
            LocalDateTime.parse(model.getCreatedAt(), DATE_FORMATTER),
            LocalDateTime.parse(model.getUpdatedAt(), DATE_FORMATTER)
        );
    }
} 