package com.fenrir.guruguru_spring.domain.review.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class ReviewNotMatchUserException extends BusinessException {
    public ReviewNotMatchUserException() {
        super(ErrorCode.REVIEW_NOT_MATCH_USER);
    }
}
