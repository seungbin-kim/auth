package com.example.auth.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATED_EMAIL("이메일 중복입니다."),
    DUPLICATED_USERNAME("이름 중복입니다."),

    ROLE_NOT_FOUND("올바른 ROLE이 아닙니다."),

    USER_NOT_FOUND("유저 정보를 찾을 수 없습니다.")

    ;

    private final String message;

}
