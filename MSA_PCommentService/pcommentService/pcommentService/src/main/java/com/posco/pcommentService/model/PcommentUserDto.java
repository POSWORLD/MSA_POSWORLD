package com.posco.pcommentService.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Entity
public class PcommentUserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer pid;


    private String name;


    private String content;

    @UpdateTimestamp
    private LocalDateTime wdate = LocalDateTime.now();
}
