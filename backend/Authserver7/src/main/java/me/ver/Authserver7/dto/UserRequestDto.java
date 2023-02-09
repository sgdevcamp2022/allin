package me.ver.Authserver7.dto;

import lombok.*;
import me.ver.Authserver7.domain.Authority;
import me.ver.Authserver7.domain.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String email;
    private String password;
    private String userName;

    private String nickName;
    private Set<Authority> authorities;

    public User toMember(PasswordEncoder passwordEncoder, Set<Authority> authorities) {
        return User.builder()
            .email(email)
            .password(passwordEncoder.encode(password))
            .userName(userName)
            .nickName(nickName)
            .authorities(authorities)
            .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}