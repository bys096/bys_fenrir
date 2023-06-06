package com.fenrir.guruguru_spring.domain.user.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class UserInvalidException extends BusinessException {
    public UserInvalidException() {
        super(ErrorCode.USER_INVALID);
    }
}
