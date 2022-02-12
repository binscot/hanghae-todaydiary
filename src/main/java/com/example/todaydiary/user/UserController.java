package com.example.todaydiary.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.todaydiary.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입 요청 처리
    @PostMapping("/api/signup")
    public String registerUser(UserRequestDto requestDto) {  //SignupRequestDto 앞에 @RequestParam이 생략
        userService.registerUser(requestDto);
        return "redirect:/api/login";
    }

    // 회원 로그인 페이지
    @GetMapping("/api/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/api/signup")
    public String signup() {
        return "signup";
    }
}