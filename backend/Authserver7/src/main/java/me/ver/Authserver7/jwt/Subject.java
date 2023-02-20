package me.ver.Authserver7.jwt;

import lombok.Getter;

@Getter
public class Subject {

    private final Long id;

    private final String email;

    private final String nickName;

    private final String type;

    private Subject(Long id, String email, String nickname, String type) {
        this.id = id;
        this.email = email;
        this.nickName = nickname;
        this.type = type;
    }

    public static Subject accesstoken(Long id, String email, String nickname) {
        return new Subject(id, email, nickname, "accesstoken");
    }

    public static Subject refreshtoken(Long id, String email, String nickname) {
        return new Subject(id, email, nickname, "refreshtoken");
    }
}