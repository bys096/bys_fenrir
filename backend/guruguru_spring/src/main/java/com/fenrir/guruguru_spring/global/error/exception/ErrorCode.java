package com.fenrir.guruguru_spring.global.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "間違った入力です。"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C002", "間違ったタイプです。"),
    MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C003", "パラメーターの数が足りません。"),
    NOT_EXIST_API(HttpStatus.BAD_REQUEST, "C004", "間違ったAPI要請です。"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C005", "サポートされていないメソッドです。"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "C006", "アクセス権限がありません。"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C007", "サーバーエラーです。"),


    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "存在しないユーザーです。"),
    USER_INVALID(HttpStatus.BAD_REQUEST, "U002", "権限がないユーザーです。"),

    // Store
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "存在しない店です。"),
    STORE_INVALID(HttpStatus.BAD_REQUEST, "S002", "権限がない店です。"),

    // Owner
    OWNER_DUPLICATE(HttpStatus.BAD_REQUEST, "O001", "もう登録済みの店です。"),
    OWNER_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "O002", "登録申請したログがありません。"),

    // File
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "存在しないファイルです。"),

    // Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "R001", "存在しないレビューです。"),
    REVIEW_DUPLICATE(HttpStatus.BAD_REQUEST, "R002", "このレビューはもう作成済みです。"),
    REVIEW_NOT_MATCH_USER(HttpStatus.FORBIDDEN, "R003", "ユーザーが作成したレビューまたはオーナーではありません。"),
    REVIEW_ALREADY_LIKED(HttpStatus.BAD_REQUEST, "R004", "いいねが登録されているレビューです。"),

    // Reply
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "RP001", "存在しないコメントです。"),
    REPLY_INVALID(HttpStatus.BAD_REQUEST, "RP002", "オーナではありません。"),
    REPLY_NOT_MATCH_USER(HttpStatus.FORBIDDEN, "RP003", "ユーザーが作成したレビューまたはオーナーではありません。");

    private final HttpStatus status;
    private final String code;
    private final String message;
    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
