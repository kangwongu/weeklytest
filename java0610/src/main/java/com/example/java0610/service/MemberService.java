package com.example.java0610.service;

import com.example.java0610.domain.Member;
import com.example.java0610.dto.member.*;
import com.example.java0610.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long updateMember(Long id, MemberModifyRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 ID가 존재하지 않습니다.")
        );
        member.update(requestDto);
        return id;
    }

    // 추천
    @Transactional
    public List<Member> recommendMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 ID가 존재하지 않습니다.")
        );
        return memberRepository.findAllByAgeEqualsAndGenderNot(member.getAge(), member.getGender());
//
//        List<Member> memberList = memberRepository.findAll();
//        List<Member> recommendList = new ArrayList<>();
//
//        int myGender = member.getGender();
//
//        for (Member member1 : memberList) {
//            if (myGender != member1.getGender()) {
//                recommendList.add(member1);
//            }
//        }
//        return recommendList;
    }

    public Member showUser(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 ID가 존재하지 않습니다.")
        );
        return member;
    }

    public Long createUser(MemberRequestDto requestDto) {
        Member member = Member.builder()
                .age(requestDto.getAge())
                .email(requestDto.getEmail())
                .gender(requestDto.getGender())
                .name(requestDto.getName())
                .build();

        memberRepository.save(member);
        return member.getId();
    }

    public Long deleteMember(Long id) {
        memberRepository.deleteById(id);
        return id;
    }

    // 로그인
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail());
        if (member == null) {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }

        if (!requestDto.getEmail().equals(member.getEmail())) {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }

        return MemberLoginResponseDto.builder()
                .email(member.getEmail())
                .build();
    }
}
