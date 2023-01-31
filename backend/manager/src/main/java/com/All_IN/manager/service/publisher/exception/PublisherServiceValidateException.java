package com.All_IN.manager.service.publisher.exception;

import com.All_IN.manager.service.room.exception.RoomServiceException;
import lombok.Getter;

@Getter
public class PublisherServiceValidateException extends RuntimeException {

    private final String code;

    public PublisherServiceValidateException(PublisherServiceException publisherServiceException) {
        super(publisherServiceException.getMessage());
        this.code = publisherServiceException.getCode();
    }

}
