package com.example.java0610.repository;

import com.example.java0610.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByAgeEqualsAndGenderNot(int age, int gender);

    Member findByEmail(String email);

}
