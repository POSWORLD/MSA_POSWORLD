package com.posworld.userService.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "usertbl")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String gender;

    private String prophoto;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) { this.pw = pw; }

    @Builder
    public Member(Long id, String userid, String pw, String name, String gender, String prophoto, Authority authority) {
        this.id = id;
        this.userid = userid;
        this.pw = pw;
        this.name = name;
        this.gender = gender;
        this.prophoto = prophoto;
        this.authority = authority;
    }
}
