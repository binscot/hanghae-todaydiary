package com.example.todaydiary.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class UserUpdateDto {

    @NotNull
    private String nickname;

    private String user_profile;
}
