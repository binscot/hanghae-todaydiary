package com.example.todaydiary.diary;

import lombok.Getter;


@Getter
public class DiaryRequestDto {
    private Long id;
    private String emotion;
    private String tag;
    private String image_url;
    private String title;
    private String content;
    private Boolean is_open;
}
