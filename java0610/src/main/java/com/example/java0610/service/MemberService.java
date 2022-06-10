package com.example.java0610.service;

import com.example.java0610.domain.Member;
import com.example.java0610.dto.MemberModifyRequestDto;
import com.example.java0610.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long update(Long id, MemberModifyRequestDto requestDto) {
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
        List<Member> memberList = memberRepository.findAll();
        List<Member> recommendList = null;

        int myGender = member.getGender();

        for (Member member1 : memberList) {
            if (myGender != member1.getGender()) {
                recommendList.add(member1);
            }
        }
        return recommendList;

    }
}
