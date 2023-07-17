package com.project.project.service;

import com.project.project.domain.User;
import com.project.project.dto.AddUserRequest;
import com.project.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {  //AddUserRequest 객체를 인수로 받는 회원 정보

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호화, 패스워드 저장 할 때 시큐리티 설정하고 패스워드 인코딩 용으로 등록한 빈 사용해 함호화 한 후 저장함
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
