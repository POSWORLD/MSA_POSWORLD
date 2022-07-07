package com.posco.photoSelectService.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Component
@Table(name = "phototbl")
@DynamicInsert //Insert시 null인 컬럼 제외( default 로 지정해놓은 값이 들어가게끔 유도하기 위해 사용 )
public class PhotoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    private String title;
    private String content;
    private String img;
    @UpdateTimestamp
    private Timestamp wdate;
}