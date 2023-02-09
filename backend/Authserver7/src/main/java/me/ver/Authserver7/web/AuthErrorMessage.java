package me.ver.Authserver7.web;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum AuthErrorMessage {
    EXIST_AUTH("101","exist AUTH"),

    NO_SUCH_AUTH("102","no such AUTH");

    private final String code;
    private final String message;
}

