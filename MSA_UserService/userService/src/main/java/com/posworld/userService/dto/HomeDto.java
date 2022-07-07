package com.posworld.userService.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class HomeDto {
    private Integer userid;
    private String title;
    private String photo;
    private String content;
    private String bgm;
    private String total;
}
