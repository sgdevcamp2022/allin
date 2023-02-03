package com.example.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
  REQUEST_DATA_NOT_VALID("001", "요청 데이터가 올바르지 않습니다."),
  NONEXISTENT_TOPIC("002", "존재하지 않는 topic입니다.");

  private final String code;
  private final String message;
}
