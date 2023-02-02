package com.All_IN.manager.service.braodCast.exception;

public enum BroadCastServiceException {

    ALREADY_ON_LIVE("101", "already on live"),
    NO_MATCH_LIVE("102", "no match live"),
    ;

    private String message;
    private String code;

    BroadCastServiceException(String code, String message) {
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
