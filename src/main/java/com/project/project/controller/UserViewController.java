package com.project.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "login"; //'/login'경로로 들어오면 login.html 반환
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; //'/signup'경로로 들어오면 signup.html 반환
    }
}
