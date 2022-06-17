package com.example.java0610.domain;

import com.example.java0610.dto.member.MemberModifyRequestDto;
import com.example.java0610.dto.member.MemberRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Builder
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
