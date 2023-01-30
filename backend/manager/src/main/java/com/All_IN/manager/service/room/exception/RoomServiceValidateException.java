package com.All_IN.manager.service.room.exception;

import lombok.Getter;

@Getter
public class RoomServiceValidateException extends RuntimeException {

    private final String code;

    public RoomServiceValidateException(RoomServiceException roomServiceException) {
        super(roomServiceException.getMessage());
        this.code = roomServiceException.getCode();
    }

}
