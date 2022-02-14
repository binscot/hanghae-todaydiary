package com.example.todaydiary.diary;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DiaryResponseDto {
    private final Long id;
    private final String title;
    private final String nickname;
    private final Long uid;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String image_url;
    private final String emotion;
    private final String tag;
    private final Boolean is_open;

    public DiaryResponseDto(Long id,String title,String nickname, Long uid, String content, LocalDateTime createdAt, LocalDateTime modifiedAt,
                            String image_url, String emotion, String tag, Boolean is_open) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.uid = uid;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.image_url = image_url;
        this.emotion = emotion;
        this.tag = tag;
        this.is_open = is_open;
    }
}
