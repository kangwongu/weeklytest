package com.example.java0610.controller;

import com.example.java0610.domain.Member;
import com.example.java0610.dto.MemberModifyRequestDto;
import com.example.java0610.dto.MemberRequestDto;
import com.example.java0610.repository.MemberRepository;
import com.example.java0610.service.MemberService;
import lombok.RequiredArgsConstructor;
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
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 ID가 존재하지 않습니다.")
        );
        return member;
    }

    // 생성
    @PostMapping("/create")
    public Long createUser(@RequestBody MemberRequestDto requestDto) {
        Member member = new Member(requestDto);
        memberRepository.save(member);
        return member.getId();
    }

    // 수정
    @PutMapping("/update/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody MemberModifyRequestDto requestDto) {
        return memberService.update(id, requestDto);
    }

    // 삭제
    @DeleteMapping("/delete/{id}")
    public Long deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
        return id;
    }

    // 추천
    @GetMapping("/recommend/{id}")
    public List<Member> recommendMember(@PathVariable Long id) {
        return memberService.recommendMember(id);
    }

}
