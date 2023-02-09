package me.ver.Authserver7.web;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {

    private final Timestamp timestamp;
    private final String code;
    private final String error;
    private final String message;

    public static ExceptionResponse of(String code, String error, String message) {
        return new ExceptionResponse(Timestamp.valueOf(LocalDateTime.now()), code, error, message);
    }
}

