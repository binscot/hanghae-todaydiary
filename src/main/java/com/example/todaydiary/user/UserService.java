package com.example.todaydiary.user;

import com.example.todaydiary.security.JwtTokenProvider;
import com.example.todaydiary.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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


    //중복확인 서비스
    //CheckId에 중복확인성공시 반환하는 객체들이 있다. 중복된 아이디가 있을경우 fals를 이용하여 오류를 내주고 그렇지 않으면 가능하다고 성공시켜줍니다.
    public ReturnCheckId checkId(UserRequestDto requestDto) {
        ReturnCheckId returnCheckId = new ReturnCheckId();
        Optional<User> member = userRepository.findByUsername(requestDto.getUsername());
        if (member.isPresent()) {
            returnCheckId.setOk(false);
            returnCheckId.setMsg("중복된 ID가 존재합니다.");
        } else {
            returnCheckId.setOk(true);
            returnCheckId.setMsg("사용 가능한 ID 입니다.");
        }
        return returnCheckId;
    }


    //로그인 서비스
    //로그인 dto에 username과 password를 가지고 존재하는지 확인을 해줍니다 userrepository를 이용하여 db에서 체크
    //존재하지 않거나 비밀번호가 맞지 않을시 오류를 내주고 그렇지 않을경우 토큰을 발행합니다.
    public ReturnUserDto login(LoginDto loginDto) {
        ReturnUserDto returnUserDto = new ReturnUserDto();
        {
            User member = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
            }
            returnUserDto.setToken(jwtTokenProvider.createToken(member.getUsername()));
            returnUserDto.setUsername(member.getUsername());
            returnUserDto.setNickname(member.getNickname());
            returnUserDto.setUser_profile(member.getUser_profile());
            return returnUserDto;
        }
    }

    public UserresponseDto UserInfo(UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        String username = user.getUsername();
        String nickname = user.getNickname();
        String User_profile = user.getUser_profile();
        if (username == null)

            throw new NullPointerException("정보가 안들어왔습니다.");
        UserresponseDto userresponseDto = new UserresponseDto(username, nickname, User_profile);
        return userresponseDto;
    }

    @Transactional
    public User updateUser(Long id, UserUpdateDto userUpdateDto) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        String nickname = userUpdateDto.getNickname();
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }
        if (nickname == null) {
            throw new NullPointerException("닉네임을 입력해주세요");
        }
        user.updateUser(userUpdateDto);
        userRepository.save(user);
        return user;
    }
}