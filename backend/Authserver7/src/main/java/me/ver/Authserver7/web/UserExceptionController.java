package me.ver.Authserver7.web;

import static me.ver.Authserver7.web.UserErrorMessage.EXIST_USER;
import static me.ver.Authserver7.web.UserErrorMessage.NO_SUCH_USER;
import static me.ver.Authserver7.web.UserErrorMessage.NO_MATCH_PASSWORD;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class UserExceptionController {
    private static final String SERVER_NUM = "1";

    // EXIST_USER
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBindException(BindException e) {
        log.warn("handleBindException: {}", e);
        return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
                EXIST_USER.getCode()),
            e.getClass().getName(), EXIST_USER.getMessage());
    }

    // NO_SUCH_USER
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        log.warn("handleInternalAuthenticationServiceException: {}", e);
        return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
                NO_SUCH_USER.getCode()),
            e.getClass().getName(), NO_SUCH_USER.getMessage());
    }

    // NO_MATCH_PASSWORD
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("handleIllegalArgumentException: {}", e);
        return ExceptionResponse.of(calculateCode(HttpStatus.BAD_REQUEST,
                NO_MATCH_PASSWORD.getCode()),
            e.getClass().getName(), NO_MATCH_PASSWORD.getMessage());
    }

    private String calculateCode(HttpStatus status, String code) {
        return status.value() + SERVER_NUM + code;
    }
}
