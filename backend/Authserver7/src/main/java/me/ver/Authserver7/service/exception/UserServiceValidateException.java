package me.ver.Authserver7.service.exception;

import lombok.Getter;

@Getter
public class UserServiceValidateException extends RuntimeException {

    private final String code;

    public UserServiceValidateException(UserServiceException publisherServiceException) {
        super(publisherServiceException.getMessage());
        this.code = publisherServiceException.getCode();
    }

}
