package me.ver.Authserver7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ver.Authserver7.domain.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String email;
    private String userName;
    private String nickName;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(
            user.getEmail(), user.getUserName(), user.getNickName());
    }
}