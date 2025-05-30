package com.example.salt.service;

import com.example.salt.dto.MemberDTO;
import com.example.salt.entity.MemberEntity;
import com.example.salt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean saveIfNew(String username) {
        if(!memberRepository.existsByUsername(username)) {
            MemberDTO dto = new MemberDTO();
            dto.setUsername(username);

            MemberEntity entity = MemberEntity.toMemberEntity(dto);
            memberRepository.save(entity);
            return true;
        }
        else {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }
    }

    public void updateGender(String username,Integer gender){
//        if (memberRepository.findByUsername(username)) {
//            MemberDTO dto = new MemberDTO();
//            dto.setGender(gender);
//
//            MemberEntity entity = MemberEntity.toMemberEntity(dto);
//
//        }
//        else {
//            throw new IllegalArgumentException("사용자를 찾을 수 없습니다."));
//        }

        MemberEntity member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        member.setGender(gender);
        memberRepository.save(member);
    }

}
