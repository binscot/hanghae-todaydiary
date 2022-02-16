package com.example.todaydiary.diary;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DiaryResponseDto {
    private final Long id;
    private final String title;
    private final String nickname;
    private final Long uid;
    private final String user_profile;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List <ImageUrl> imageUrls;
    private final String emotion;
    private final String tag;
    private final Boolean is_open;

    public DiaryResponseDto(Long id,String title,String nickname, Long uid, String user_profile,String content, LocalDateTime createdAt, LocalDateTime modifiedAt,
                            List<ImageUrl> imageUrlList , String emotion, String tag, Boolean is_open) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.uid = uid;
        this.user_profile = user_profile;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.imageUrls = imageUrlList;
        this.emotion = emotion;
        this.tag = tag;
        this.is_open = is_open;
    }
}
