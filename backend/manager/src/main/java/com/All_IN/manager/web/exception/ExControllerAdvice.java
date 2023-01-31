package com.All_IN.manager.web.exception;

import com.All_IN.manager.service.publisher.exception.PublisherServiceValidateException;
import com.All_IN.manager.service.room.exception.RoomServiceValidateException;
import com.All_IN.manager.web.response.ApiResponse;
import com.All_IN.manager.web.response.ApiResponseGenerator;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    private static void logger(Exception exception) {
        log.error(exception.getClass()
                        .getSimpleName() + " = [{}][{}]",
                exception.getClass(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({PublisherServiceValidateException.class})
    public ApiResponse<ApiResponse.FailureBody> PublisherExHandler(PublisherServiceValidateException exception) {
        logger(exception);
        return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value() + exception.getCode(),
            exception.getClass()
                .getSimpleName(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RoomServiceValidateException.class})
    public ApiResponse<ApiResponse.FailureBody> RoomExHandler(RoomServiceValidateException exception) {
        logger(exception);
        return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value() + exception.getCode(),
            exception.getClass()
                .getSimpleName(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalStateException.class})
    public ApiResponse<ApiResponse.FailureBody> illegalStateExHandler(IllegalStateException exception) {
        logger(exception);
        String defaultMessage = exception.getMessage();
        return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value() + "001",
                exception.getClass()
                        .getSimpleName(), defaultMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<ApiResponse<ApiResponse.FailureBody>> bindingExHandler(BindException exception) {
        logger(exception);
        List<ApiResponse<ApiResponse.FailureBody>> errorResults = new ArrayList<>();
        exception.getAllErrors()
                .forEach(error -> {
                    FieldError fieldError = (FieldError) error;
                    String field = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    ApiResponse<ApiResponse.FailureBody> bindingException = ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value() + "001", exception.getClass()
                            .getSimpleName(), field + " 필드 입력이 필요합니다. " + defaultMessage);
                    errorResults.add(bindingException);
                });
        return errorResults;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiResponse<ApiResponse.FailureBody> exHandler(Exception exception) {
        logger(exception);
        String defaultMessage = exception.getMessage();
        return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value() + "000",
                exception.getClass()
                        .getSimpleName(), defaultMessage);
    }
}
