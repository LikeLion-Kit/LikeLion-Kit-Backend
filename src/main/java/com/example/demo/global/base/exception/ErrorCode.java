package com.example.demo.global.base.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력 값입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "잘못된 타입입니다."),
    MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST,  "인자가 부족합니다."),
    NOT_EXIST_API(HttpStatus.BAD_REQUEST, "요청 주소가 올바르지 않습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "사용할 수 없는 메서드입니다."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN,  "접근 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다."),

    // 400 error
    INVALID_JSON(HttpStatus.BAD_REQUEST, "JSON 파싱 오류입니다."),

    // 409 error
    EXIST_SAME_NAME(HttpStatus.CONFLICT, "이미 사용중인 이름 입니다."),
    EXIST_SAME_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 이메일 입니다."),



    ;

    private final HttpStatus status;
    private final String message;
}
