package com.example.todaydiary.user;

import com.example.todaydiary.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public User registerUser(UserRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }
        System.out.println(requestDto.getUsername());
        if (requestDto.getUsername() == null) {
            throw new NullPointerException("아이디를 입력해주세요");
        }
        if (Objects.equals(requestDto.getUsername(), "")) {
            throw new NullPointerException("아이디를 입력해주세요!!!!!!!!!");
        }
        if (requestDto.getNickname() == null) {
            throw new NullPointerException("닉네임을 입력해주세요");
        }
        if (requestDto.getPassword() == null) {
            throw new NullPointerException("비밀번호를 입력해주세요");
        }
        if (Objects.equals(requestDto.getPassword(), "")) {
            throw new NullPointerException("비밀번호를 입력해주세요!!!!!!!!!!!!");
        }
        if (Objects.equals(requestDto.getNickname(), "")) {
            throw new NullPointerException("닉네입을 입력해주세요!!!!!!!!!!!!!!!");
        }


        String nickname = requestDto.getNickname();

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String user_profile = requestDto.getUser_profile();

// 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, nickname, password, user_profile, role);


        return userRepository.save(user);
    }


    //로그인 서비스
    //로그인 dto에 username과 password를 가지고 존재하는지 확인을 해줍니다 userrepository를 이용하여 db에서 체크
    //존재하지 않거나 비밀번호가 맞지 않을시 오류를 내주고 그렇지 않을경우 토큰을 발행합니다.
    public ReturnUser login(LoginDto loginDto) {
        ReturnUser returnUser = new ReturnUser();
        try {

            User member = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
            }
            returnUser.setToken(jwtTokenProvider.createToken(member.getUsername()));
            returnUser.setUsername(member.getUsername());
            return returnUser;
        } catch (IllegalArgumentException e) {
            returnUser.setMsg(e.getMessage());
            return returnUser;
        }
    }
}