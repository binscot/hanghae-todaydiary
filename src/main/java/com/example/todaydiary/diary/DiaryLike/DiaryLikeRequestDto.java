package com.example.todaydiary.diary.DiaryLike;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DiaryLikeRequestDto {
    private User user;
    private Diary diary;
}