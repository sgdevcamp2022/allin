package com.example.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExceptionMessage {
  REQUEST_DATA_NOT_VALID("001", "요청 데이터가 올바르지 않습니다.");

  private final String code;
  private final String message;

  public static ExceptionMessage findByMessage(String message) {
    for (ExceptionMessage e : values()) {
      if (e.getMessage().equals(message)) {
        return e;
      }
    }
    return null;
  }
}
