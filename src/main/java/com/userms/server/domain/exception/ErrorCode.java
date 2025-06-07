package com.userms.server.domain.exception;

public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND", "Usuario no encontrado"),
    INVALID_PASSWORD("INVALID_PASSWORD", "Contraseña incorrecta"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", "Credenciales inválidas");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
} 