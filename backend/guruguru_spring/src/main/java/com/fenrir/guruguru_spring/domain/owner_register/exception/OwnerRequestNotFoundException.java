package com.fenrir.guruguru_spring.domain.owner_register.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class OwnerRequestNotFoundException extends BusinessException {
    public OwnerRequestNotFoundException() {
        super(ErrorCode.OWNER_REQUEST_NOT_FOUND);
    }
}
