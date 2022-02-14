package com.example.todaydiary.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;


    // 회원 가입 요청 처리
    @PostMapping("/api/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto requestDto) {  //SignupRequestDto 앞에 @RequestParam이 생략
        System.out.println(requestDto);
        User user =userService.registerUser(requestDto);
        return ResponseEntity.ok(user);
    }





    @PostMapping("/post")
    public ResponseEntity<UserRequestDto> showPost(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userRequestDto);
    }

//    // 회원 로그인 페이지
//    @GetMapping("/api/login")
//    public String login() {
//        return "login";
//    }
//
//    // 회원 가입 페이지
//    @GetMapping("/api/signup")
//    public String signup() {
//        return "signup";
//    }
}