package me.ver.Authserver7.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil() { }


    public static Long getLoginMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("로그인 유저 정보가 없습니다.");
        }

        Long LoginId = Long.parseLong(authentication.getName());
        return LoginId;
    }
}