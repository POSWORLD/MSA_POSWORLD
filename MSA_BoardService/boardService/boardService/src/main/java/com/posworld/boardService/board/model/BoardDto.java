package com.posco.posworld.board.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="boardtbl")
@Component
@DynamicInsert
public class BoardDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    private Integer homeid;

    private String content;

    @UpdateTimestamp
    private Timestamp wdate;

    private Integer friendid;

}
