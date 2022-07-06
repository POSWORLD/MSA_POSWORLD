package com.example.homeService.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@Table(name = "hometbl")
@DynamicInsert
public class HomeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    private String title;
    private String photo;
    private String content;
    private String bgm;
    private String total;
}
