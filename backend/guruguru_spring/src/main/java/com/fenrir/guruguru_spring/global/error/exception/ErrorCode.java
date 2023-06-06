package com.fenrir.guruguru_spring.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력 값입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C002", "잘못된 타입입니다."),
    MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C003", "입력된 인자의 수가 부족합니다."),
    NOT_EXIST_API(HttpStatus.BAD_REQUEST, "C004", "요청 API 주소가 올바르지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C005", "지원되지 않는 메서드입니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "C006", "접근 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C007", "서버 에러입니다."),


    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "存在しないユーザーです。"),
    USER_INVALID(HttpStatus.BAD_REQUEST, "U002", "権限がないユーザーです。"),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "存在しない店です。"),
    STORE_INVALID(HttpStatus.BAD_REQUEST, "S002", "権限がない店です。"),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "R001", "존재하지 않는 리뷰입니다."),
    REVIEW_DUPLICATE(HttpStatus.BAD_REQUEST, "R002", "이미 리뷰를 작성한 작품입니다."),
    REVIEW_NOT_MATCH_USER(HttpStatus.FORBIDDEN, "R003", "유저가 작성한 리뷰가 아닙니다."),
    REVIEW_ALREADY_LIKED(HttpStatus.BAD_REQUEST, "R004", "이미 좋아요가 되어 있습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
