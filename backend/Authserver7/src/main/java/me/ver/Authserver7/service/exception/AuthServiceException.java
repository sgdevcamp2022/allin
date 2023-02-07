package me.ver.Authserver7.service.exception;

public enum AuthServiceException {

    EXIST_AUTH("1101", "exist AUTH"),

    NO_SUCH_AUTH("1102", "no such AUTH"),
    ALREADY_GENERATE_PASSWORD("1103", "already generate password"),
    NO_PASSWORD("1104", "no generated password"),
    NO_MATCH_PASSWORD("1105", "no match password"),
    ;

    private String message;
    private String code;

    AuthServiceException(String code, String message) {
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

