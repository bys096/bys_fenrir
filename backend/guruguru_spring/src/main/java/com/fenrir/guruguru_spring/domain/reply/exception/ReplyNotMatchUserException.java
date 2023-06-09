package com.fenrir.guruguru_spring.domain.reply.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class ReplyNotMatchUserException extends BusinessException {

    public ReplyNotMatchUserException() {
        super(ErrorCode.REPLY_NOT_MATCH_USER);
    }

}
