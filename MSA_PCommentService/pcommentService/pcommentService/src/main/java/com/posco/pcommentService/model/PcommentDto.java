package com.posco.pcommentService.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Component
@DynamicInsert
@Table(name="pcommenttbl")
public class PcommentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer pid;


    private Integer userid;


    private String content;

    @UpdateTimestamp
    private LocalDateTime wdate = LocalDateTime.now();
}
