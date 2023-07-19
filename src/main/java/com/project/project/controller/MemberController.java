package com.project.project.controller;

import com.project.project.dto.MemberDTO;
import com.project.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor  //생성자 사용 가능하게 만들어줌
public class MemberController {

    //생성자 주입
    private final MemberService memberService;


    @GetMapping("/user/login") //로그인 페이지 출력 요청
    public String loginForm() {
        return "login";  //login.html 찾음
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);

        if(loginResult != null) {
            //로그인 성공

            session.setAttribute("loginEmail", loginResult.getEmail());

            return "main";
        } else {
            //로그인 실패
            return "login";
        }
    }


    @GetMapping("/user/signup")  //회원가입 페이지 출력 요청
    public String signupForm() {
        return "signup"; //signup.html 찾음
    }

    //회원가입
    @PostMapping("/user/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO) {  //-> service로 감
        System.out.println("MemberController.signup");  //soutm
        System.out.println("memberDTO = " + memberDTO);
        memberService.signup(memberDTO);
        return "index";
    }

}
