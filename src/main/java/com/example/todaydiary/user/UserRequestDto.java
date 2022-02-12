package com.example.todaydiary.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    private String username;
    private String nickname;
    private String password;
    private String userimage;
    private boolean admin = false;
    private String adminToken = "";
}
