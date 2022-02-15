package com.example.todaydiary.user;

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

    // 로그인
    @PostMapping("/api/login")
    public ReturnUserDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    // 유저정보 전달.
    @PostMapping("/api/user")
    public ResponseEntity<String> UserInfo(@AuthenticationPrincipal ReturnUserDto returnUserDto) {
        String username = returnUserDto.getUsername();
        String nickname = returnUserDto.getNickname();
        String userprofile = returnUserDto.getUser_profile();
        if (username == null) {
            throw new NullPointerException("정보가 안들어갔엉");
        }
        return ResponseEntity.ok(username);
    }
}

//    @GetMapping("/api/user")
//    public ResponseEntity<User> UserInfo(@RequestParam String token) {
//        return userService.UserInfo(token);
//    }
