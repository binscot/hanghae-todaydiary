package com.example.todaydiary.diary.DiaryLike;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class DiaryLike {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Diary diary;

    public DiaryLike(DiaryLikeRequestDto diaryLikeRequestDto){
        this.user = diaryLikeRequestDto.getUser();
        this.diary = diaryLikeRequestDto.getDiary();
    }


}
