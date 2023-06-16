package com.fenrir.guruguru_spring.domain.owner_register.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class OwnerDuplicateException extends BusinessException {
    public OwnerDuplicateException() {
        super(ErrorCode.OWNER_DUPLICATE);
    }
}
