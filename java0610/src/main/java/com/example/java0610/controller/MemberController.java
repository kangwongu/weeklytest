package com.example.java0610.controller;

import com.example.java0610.domain.Member;
import com.example.java0610.dto.member.*;
import com.example.java0610.repository.MemberRepository;
import com.example.java0610.service.MemberService;
import com.example.java0610.util.JwtTokenCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    // 조회
    @GetMapping("/read/{id}")
    public Member showUser(@PathVariable Long id) {
        Member member = memberService.showUser(id);
        return member;
    }

    // 생성
    @PostMapping("/create")
    public Long createUser(@RequestBody MemberRequestDto requestDto) {
        return memberService.createUser(requestDto);
    }

    // 수정
    @PutMapping("/update/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody MemberModifyRequestDto requestDto) {
        return memberService.updateMember(id, requestDto);
    }

    // 삭제
    @DeleteMapping("/delete/{id}")
    public Long deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }

    // 추천
    @GetMapping("/recommend/{id}")
    public List<Member> recommendMember(@PathVariable Long id) {
        return memberService.recommendMember(id);
    }

    // 로그인, 로그인 성공하면 토큰 반환
    @PostMapping("/login")
    public MemberTokenDto login(@RequestBody MemberLoginRequestDto requestDto) {
        MemberLoginResponseDto loginMember = memberService.login(requestDto);
        // 토큰 만들기
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginMember.getEmail(), null);
        String token = JwtTokenCreator.createToken(authentication);

        // 로그인 성공한 클라이언트에게 토큰 반환
        return MemberTokenDto.builder()
                .token(token)
                .build();
    }
}
