package com.posco.pcommentService.model;

import lombok.Data;

@Data
public class UserResponseDto {
    private Integer id;
    private String userid;
    private String pw;
    private String name;
    private String gender;
    private String prophoto;
    private String authority;
}
