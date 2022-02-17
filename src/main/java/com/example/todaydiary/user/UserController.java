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

    // ID 중복 체크.
    @PostMapping("/api/signup/checkId")
    public ReturnCheckId checkId(@RequestBody UserRequestDto requestDto){
        return userService.checkId(requestDto);
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

    // 유저정보 수정.
    @PutMapping("/api/user/{userId}")
    public Long updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateDto userUpdateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updateUser(userId, userUpdateDto, userDetails);
        return userId;
    }

}