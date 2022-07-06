package com.posworld.userService.dto;

import com.posworld.userService.entity.Authority;
import com.posworld.userService.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String userid;
    private String pw;
    private String name;
    private String gender;
    private String prophoto;


    public Member toUser(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .userid(userid)
                .pw(passwordEncoder.encode(pw))
                .name(name)
                .gender(gender)
                .prophoto(prophoto)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(userid, pw);
    }
}