package com.posworld.boardService.board.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Component
@Entity
@Table(name="boardtbl")
@DynamicInsert
public class SelectJoinDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private Integer homeid;
    private String content;
    private Timestamp wdate;
    private Integer friendid;
    private String friendimg;
    private String friendname;
}