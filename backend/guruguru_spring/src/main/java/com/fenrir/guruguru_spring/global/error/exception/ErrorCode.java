package com.fenrir.guruguru_spring.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "存在しないユーザーです。"),
    USER_INVALID(HttpStatus.BAD_REQUEST, "U002", "権限がないユーザーです。"),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "存在しない店です。"),
    STORE_INVALID(HttpStatus.BAD_REQUEST, "S002", "権限がない店です。");

    private final HttpStatus status;
    private final String code;
    private final String message;
    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
