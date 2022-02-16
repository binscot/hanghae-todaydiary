package com.example.todaydiary.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserUpdateDto {
    private String nickname;
    private String user_profile;
}
