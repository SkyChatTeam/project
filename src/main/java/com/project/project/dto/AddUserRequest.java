package com.project.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {  //사용자 정보 담는 객체
    private String email;
    private String password;
    private String username;
}
