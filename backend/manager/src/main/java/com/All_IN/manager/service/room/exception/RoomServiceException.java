package com.All_IN.manager.service.room.exception;

public enum RoomServiceException {

    NO_MATCH_ROOM("201", "no match room"),
    NO_ROOM_INFORMATION("202", "no room information"),
    NO_MATCH_ROOM_INFO("203", "no match room info"),
    ALREADY_HAVE_ROOM("204", "publisher already has room"),
    ;

    private String message;
    private String code;

    RoomServiceException(String code, String message) {
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