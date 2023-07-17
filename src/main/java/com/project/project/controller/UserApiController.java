package com.project.project.controller;

import com.project.project.dto.AddUserRequest;
import com.project.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    //회원가입 폼에서 요청 받으면 서비스 메소드로 사용자 저장한 후 로그인 페이지로 이동
    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request); //회원가입 메소드 호출
        return "redirect:/login"; //회원가입 완료 후 강제로 로그인 페이지로 이동
    }
}
