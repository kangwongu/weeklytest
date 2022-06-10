package com.example.java0610.domain;

import com.example.java0610.dto.MemberModifyRequestDto;
import com.example.java0610.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@NoArgsConstructor
@Getter
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = false)
    private String name;

    public Member(MemberRequestDto requestDto) {
        this.age = requestDto.getAge();
        this.email = requestDto.getEmail();
        this.name = requestDto.getName();
        this.gender = requestDto.getGender();
    }

    public void update(MemberModifyRequestDto requestDto) {
        this.age = requestDto.getAge();
        this.name = requestDto.getName();
        this.gender = requestDto.getGender();
    }
}
