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
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String con_image;
    private final String emotion;
    private final String tag;
    private final Boolean is_open;

    public DiaryResponseDto(Long id,String title,String nickname, Long userId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt,
                            String con_image, String emotion, String tag, Boolean is_open) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.con_image = con_image;
        this.emotion = emotion;
        this.tag = tag;
        this.is_open = is_open;
    }
}
