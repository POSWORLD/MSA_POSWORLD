package com.posco.pcommentService.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="pcomment_user_tbl")
public class PcommentUserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Integer pid;

    private Integer userid;


    private String name;


    private String content;

    @UpdateTimestamp
    private LocalDateTime wdate = LocalDateTime.now();
}