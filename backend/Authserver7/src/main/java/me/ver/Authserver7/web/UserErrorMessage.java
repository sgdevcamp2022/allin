package me.ver.Authserver7.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum UserErrorMessage {
    EXIST_USER("101", "이미 존재하는 사용자입니다."),

    NO_SUCH_USER("102", "해당하는 사용자가 없습니다."),
    NO_MATCH_PASSWORD("103", "비밀번호가 일치하지 않습니다.");

    private final String code;
    private final String message;
}