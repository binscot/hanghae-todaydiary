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


    //글 작성
    @Transactional
    public Diary createDiary(DiaryRequestDto requestDto, User user) {

        //
        String content = requestDto.getContent();
        if(requestDto.getContent()==null){
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if(content.length() >1000){
            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
        }

        Diary diary = new Diary(requestDto, user.getId());
        diaryRepository.save(diary);
        return diary;
    }


    //수정
    @Transactional
    public Diary updateDiary(
            Long id,
            DiaryRequestDto requestDto)
    {
       Diary diary = diaryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
       User user = diary.getUser();
       if(UserDetailsImpl.getUser != user){
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
    public Long deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                        .orElseThrow(()->new IllegalArgumentException("일기가 없습니다."));
            diaryRepository.deleteById(diaryId);
            return diaryId;
        }
    }
