package com.fenrir.guruguru_spring.global.error.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        log.info(this.errorCode.getMessage());
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
