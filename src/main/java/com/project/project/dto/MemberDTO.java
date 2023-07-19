package com.project.project.dto;

import com.project.project.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString  //dto 객체가 가지고 있는 필드값 출력할 때
@AllArgsConstructor  //필드를 모두 메개변수로 하는 생성자 만들어줌
@NoArgsConstructor  //기본생성자 자동으로 만들어줌
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;

    public  static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setEmail(memberDTO.getEmail());
        memberDTO.setPassword(memberDTO.getPassword());
        memberDTO.setName(memberDTO.getName());

        return memberDTO;
    }

}
