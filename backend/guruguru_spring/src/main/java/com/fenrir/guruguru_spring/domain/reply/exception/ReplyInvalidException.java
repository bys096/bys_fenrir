package com.fenrir.guruguru_spring.domain.reply.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class ReplyInvalidException extends BusinessException {

    public ReplyInvalidException() {
        super(ErrorCode.REPLY_INVALID);
    }

}
