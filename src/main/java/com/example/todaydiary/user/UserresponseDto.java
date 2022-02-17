package com.example.todaydiary.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserresponseDto {
    //로그인 되었을때 리턴되는객체들 토큰발행

    private Long id;
    private String username;
    private String nickname;
    private String user_profile;

    public UserresponseDto(Long id, String username, String nickname, String user_profile) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.user_profile = user_profile;


    }
}
