package com.posworld.photoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Component
@DynamicInsert
@Entity
@Table(name = "phototbl")
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
