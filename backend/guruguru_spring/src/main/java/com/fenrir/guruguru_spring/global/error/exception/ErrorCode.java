package com.fenrir.guruguru_spring.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "存在しないユーザです。"),
    USER_INVALID(HttpStatus.BAD_REQUEST, "U002", "権限がないユーザです。");

    private final HttpStatus status;
    private final String code;
    private final String message;
    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
