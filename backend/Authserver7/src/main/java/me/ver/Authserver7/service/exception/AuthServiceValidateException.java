package me.ver.Authserver7.service.exception;

import me.ver.Authserver7.service.exception.UserServiceException;
import lombok.Getter;

@Getter
public class AuthServiceValidateException extends RuntimeException {

    private final String code;

    public AuthServiceValidateException(UserServiceException publisherServiceException) {
        super(publisherServiceException.getMessage());
        this.code = publisherServiceException.getCode();
    }

}
