package com.fenrir.guruguru_spring.domain.owner_register.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class FileNotFoundException extends BusinessException {

    public FileNotFoundException() {
        super(ErrorCode.FILE_NOT_FOUND);
    }

}
