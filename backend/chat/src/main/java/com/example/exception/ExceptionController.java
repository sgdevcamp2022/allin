package com.example.exception;


import static com.example.exception.ExceptionMessage.REQUEST_DATA_NOT_VALID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionController {

  private static final String SERVER_NUM = "5";

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleBindException(BindException e) {
    log.warn("handleBindException: {}", e);
    return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
        REQUEST_DATA_NOT_VALID.getCode()),
      e.getClass().getName(), REQUEST_DATA_NOT_VALID.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleIllegalArgumentException(IllegalArgumentException e) {
    log.warn("handleIllegalArgumentException: {}", e);
    ExceptionMessage exceptionMessage = ExceptionMessage.findByMessage(e.getMessage());
    return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
        exceptionMessage.getCode()),
      e.getClass().getName(), exceptionMessage.getMessage());
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleIllegalStateException(IllegalStateException e) {
    log.warn("handleIllegalStateException: {}", e);
    ExceptionMessage exceptionMessage = ExceptionMessage.findByMessage(e.getMessage());
    return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
        exceptionMessage.getCode()),
      e.getClass().getName(), exceptionMessage.getMessage());
  }

  private String calculateCode(HttpStatus status, String code) {
    return status.value() + SERVER_NUM + code;
  }
}
