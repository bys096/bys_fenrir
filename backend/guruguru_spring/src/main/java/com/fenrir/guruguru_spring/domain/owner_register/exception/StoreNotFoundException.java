package com.fenrir.guruguru_spring.domain.owner_register.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class StoreNotFoundException extends BusinessException {
    public StoreNotFoundException() {
        super(ErrorCode.OWNER_DUPLICATE);
    }
}
