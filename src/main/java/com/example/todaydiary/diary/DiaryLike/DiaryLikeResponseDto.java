package com.example.todaydiary.diary.DiaryLike;


import lombok.Getter;

@Getter
public class DiaryLikeResponseDto {
    private Long diaryId;
    private Long totalLike;

    public DiaryLikeResponseDto(Long diaryId, Long totalLike){
        this.diaryId = diaryId;
        this.totalLike = totalLike;
    }
}