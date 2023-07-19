package com.project.project.entity;


import com.project.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="member_table")
//테이블역할
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(name="email", nullable = false, length = 50, unique = true) //해당 필드는 칼럼, null값 안됨. 길이 제한
    private String email; //아이디

    @Column(name="password", nullable = false, length = 100)
    private String password; //비밀번호

    @Column(name="name", nullable = false, length = 50)
    private String name; //이름



    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());

        return memberEntity;
    }
}
