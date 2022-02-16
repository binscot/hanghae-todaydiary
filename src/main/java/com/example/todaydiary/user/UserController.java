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

    // 로그인
    @PostMapping("/api/login")
    public ReturnUserDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    // 유저정보 전달.
    @PostMapping("/api/user")
    public ResponseEntity<UserresponseDto> UserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserresponseDto userresponseDto = userService.UserInfo(userDetails);

        return ResponseEntity.ok(userresponseDto);
    }
}

//    @GetMapping("/api/user")
//    public ResponseEntity<User> UserInfo(@RequestParam String token) {
//        return userService.UserInfo(token);
//    }
