package com.All_IN.manager.service.braodCast.exception;

import lombok.Getter;

@Getter
public class BroadCastServiceValidateException extends RuntimeException {

    private final String code;

    public BroadCastServiceValidateException(BroadCastServiceException broadCastServiceException) {
        super(broadCastServiceException.getMessage());
        this.code = broadCastServiceException.getCode();
    }

}
