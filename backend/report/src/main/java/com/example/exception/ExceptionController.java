package com.example.exception;


import static com.example.exception.ErrorMessage.REQUEST_DATA_NOT_VALID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionController {

  private static final String SERVER_NUM = "6";

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleMethodArgumentNotValidException(
    MethodArgumentNotValidException e) {
    log.warn("handleMethodArgumentNotValidException: {}", e);
    return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
        REQUEST_DATA_NOT_VALID.getCode()),
      e.getClass().getName(), REQUEST_DATA_NOT_VALID.getMessage());
  }

  private String calculateCode(HttpStatus status, String code) {
    return status.value() + SERVER_NUM + code;
  }
}
