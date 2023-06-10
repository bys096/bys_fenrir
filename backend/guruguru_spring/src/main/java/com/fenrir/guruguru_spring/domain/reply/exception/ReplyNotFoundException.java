package com.fenrir.guruguru_spring.domain.reply.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class ReplyNotFoundException extends BusinessException {

    public ReplyNotFoundException() {
        super(ErrorCode.REPLY_NOT_FOUND);
    }

}
