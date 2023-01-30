package com.example.chat.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
  NONEXISTENT_TOPIC("존재하지 않는 topic입니다.");
  private final String message;
}
