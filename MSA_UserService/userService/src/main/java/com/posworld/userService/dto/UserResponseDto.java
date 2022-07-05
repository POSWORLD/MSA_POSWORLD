package com.posworld.userService.dto;

import com.posworld.userService.entity.Member;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponseDto {
    private Long id;
    private String userid;
    private String name;
    private String gender;
    private String prophoto;

    public static UserResponseDto of(Member member) {
        return UserResponseDto.builder()
                .id(member.getId())
                .userid(member.getUserid())
                .name(member.getName())
                .gender(member.getGender())
                .prophoto(member.getProphoto())
                .build();
    }
}