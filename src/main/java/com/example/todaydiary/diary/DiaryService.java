package com.example.todaydiary.diary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;



    @Transactional
    public Diary createDiary(DiaryRequestDto requestDto, User user) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        //
        String content = requestDto.getContent();
        if(requestDto.getContent()==null){
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if(content.length() >1000){
            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
        }

        Diary diary = new Diary(requestDto, user);
        diaryRepository.save(diary);
        return diary;
    }


    //수정
    @Transactional
    public Diary updateDiary(
            Long id,
            DiaryRequestDto requestDto,
            PrincipalDetails principalDetails)
    {
       Diary diary = diaryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
       User user = diary.getUser();
       if(principalDetails.getUser != user){
           throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
       }
       if(requestDto.getContent()==null){
           throw new IllegalArgumentException("내용을 입력해주세요.");
       }
       if(requestDto.getContent().length() > 1000){
           throw new IllegalArgumentException ("1000자 이하로 입력해주세요.");
       }
        diary.updateDiary(requestDto);
       diaryRepository.save(diary);
        return diary;
    }


    //삭제
    @Transactional
    public void deleteDiary(Long commentId,  PrincipalDetails principalDetails) {
        User user = diaryRepository.findById(commentId).get().getUser();
        if (user!=principalDetails.getUser){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        } else {
            diaryRepository.deleteById(commentId);
        }
    }

}
