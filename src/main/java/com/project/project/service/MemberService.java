package com.project.project.service;

import com.project.project.dto.MemberDTO;
import com.project.project.entity.MemberEntity;
import com.project.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signup(MemberDTO memberDTO) {

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(memberDTO.getEmail());

        if(byEmail.isPresent()) {
            //회원 있음
            MemberEntity memberEntity = byEmail.get();

            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                //비밀번호 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                //비밀번호 불일치
                return null;
            }

        } else {
            //회원 없음
            return null;
        }

    }
}
