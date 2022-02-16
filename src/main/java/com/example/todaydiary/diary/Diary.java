package com.example.todaydiary.diary;

import com.example.todaydiary.timestamped.Timestamped;
import com.example.todaydiary.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor

public class Diary extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String emotion;

    @Column
    private String tag;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean is_open;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIARY_NAME") //오더 아이디 외래키주기
    private List<ImageUrl> imageUrlList;


    @Builder
    public Diary(DiaryRequestDto requestDto, User user, List<ImageUrl> imageUrlList1){
        this.id = requestDto.getId();
        this.user= user;
        this.emotion = requestDto.getEmotion();
        this.tag = requestDto.getTag();
        this.imageUrlList = imageUrlList1;
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.is_open = requestDto.getIs_open();
    }



    public void updateDiary(DiaryRequestDto requestDto) {
        this.emotion = requestDto.getEmotion();
        this.tag = requestDto.getTag();
        this.imageUrlList = requestDto.getImageUrlList();
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.is_open = requestDto.getIs_open();
    }
}
