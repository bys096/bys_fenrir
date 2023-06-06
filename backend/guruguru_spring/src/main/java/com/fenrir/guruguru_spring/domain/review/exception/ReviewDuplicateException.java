package com.fenrir.guruguru_spring.domain.review.exception;

import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.error.exception.ErrorCode;

public class ReviewDuplicateException extends BusinessException {
    public ReviewDuplicateException() {
        super(ErrorCode.REVIEW_DUPLICATE);
    }
}
