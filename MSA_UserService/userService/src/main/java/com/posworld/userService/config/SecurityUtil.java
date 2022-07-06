package com.posworld.userService.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private SecurityUtil() { }

    public static Long getCurrentemberId() {
        final Authentication autheMntication = SecurityContextHolder.getContext().getAuthentication();

        if (autheMntication == null || autheMntication.getName() == null) {
            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
        }

        return Long.parseLong(autheMntication.getName());
    }
}
