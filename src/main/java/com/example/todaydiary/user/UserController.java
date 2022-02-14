package com.example.todaydiary.user;

import com.example.todaydiary.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원 가입 요청 처리
    @PostMapping("/api/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto requestDto) {
        User user = userService.registerUser(requestDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/user")
    public ResponseEntity<User> login(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok(user);
    }

    // 회원 로그인 페이지
    @PostMapping("/api/login")
    public User login(@RequestBody UserRequestDto requestDto) {
        return userService.login(requestDto);
    }
//
//    // 회원 가입 페이지
//    @GetMapping("/api/signup")
//    public String signup() {
//        return "signup";
//    }
}