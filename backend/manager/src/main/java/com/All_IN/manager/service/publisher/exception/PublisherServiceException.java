package com.All_IN.manager.service.publisher.exception;

public enum PublisherServiceException {

    EXIST_PUBLISHER("101", "exist publisher"),

    NO_SUCH_PUBLISHER("102", "no such publisher"),
    ALREADY_GENERATE_PASSWORD("103", "already generate password"),
    NO_PASSWORD("104", "no generated password"),
    NO_MATCH_PASSWORD("105", "no match password"),
    ;

    private String message;
    private String code;

    PublisherServiceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
