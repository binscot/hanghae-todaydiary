package com.example.todaydiary.diary;

import com.example.todaydiary.timestamped.Timestamped;
<<<<<<< HEAD
import com.example.todaydiary.user.User;
=======
>>>>>>> origin/hj
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column
<<<<<<< HEAD
    private String image_url;
=======
    private String con_image;
>>>>>>> origin/hj

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean is_open;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;


    @Builder
    //앞에 컬럼지정해줬으니 객체 만들어줘야지. food에는 dto에서 이름이랑 가격 갖고오고, 조인컬럼한 레스토랑 넣어주자
    public Diary(DiaryRequestDto requestDto, User user){
        this.id = requestDto.getId();
        this.user= user;
        this.emotion = requestDto.getEmotion();
        this.tag = requestDto.getTag();
<<<<<<< HEAD
        this.image_url = requestDto.getImage_url();
=======
        this.con_image = requestDto.getCon_image();
>>>>>>> origin/hj
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.is_open = requestDto.getIs_open();
    }

    public void updateDiary(DiaryRequestDto requestDto) {
        this.emotion = requestDto.getEmotion();
        this.tag = requestDto.getTag();
<<<<<<< HEAD
        this.image_url = requestDto.getImage_url();
=======
        this.con_image = requestDto.getCon_image();
>>>>>>> origin/hj
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.is_open = requestDto.getIs_open();
    }
}
