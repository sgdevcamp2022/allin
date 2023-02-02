package com.example.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionResponse {

  private final String message;

  public static ExceptionResponse from(String message) {
    return new ExceptionResponse(message);
  }
}
