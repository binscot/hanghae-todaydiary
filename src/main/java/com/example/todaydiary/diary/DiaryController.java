package com.example.todaydiary.diary;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController  {

    private final DiaryRepository diaryRepository;
    private final DiaryService diaryService;

    // 게시글 조회
    @GetMapping("/api/diary")
    public List<DiaryResponseDto> getDiary() {
        List<Diary> diaries = diaryRepository.findAllByOrderByCreatedAtDesc();

        List<DiaryResponseDto> diaryResponseDtos = new ArrayList<>();
        //계층 간 작업 시 Dto를 사용하는 습관을 갖는게 중요함.
        //Controller에서 직접 Diary diary를 건드리기보다 Dto를 활용하자.
        //효율성 측면에서도 좋음. Diary 테이블(DB)에는 User의 정보 전부(id, nickname, password, email 등)가 연결되어있음.
        //내가 진짜 필요한 정보만 담아서 활용하는 것. User 전체가 아닌 User의 nickname만 뽑아서 쓰는 것이 효율적임.
        for(Diary diary : diaries){
            DiaryResponseDto diaryResponseDto = new DiaryResponseDto(
                    diary.getId(),
                    diary.getTitle(),
                    diary.getUser().getNickname(), // <-- Dto 효율성의 좋은 예시
                    diary.getUser().getUserId(),
                    diary.getContent(),
                    diary.getCreatedAt(),
                    diary.getModifiedAt(),
                    diary.getCon_image(),
                    diary.getEmotion(),
                    diary.getTag(),
                    diary.getIs_open()
            );

            diaryResponseDtos.add(diaryResponseDto);
        }

        return diaryResponseDtos;
    }


    // 게시글 디테일 조회,
    @GetMapping("/api/diary/{id}")
    public Diary getDiary(@PathVariable Long id) {
        Diary diary =  diaryRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("id가 존재하지 않습니다."));
        return diary;
    }

    // 게시글 작성
    @PostMapping("/api/diary")
    public Diary createDiary(@RequestBody DiaryRequestDto diaryRequestDto, User user) {

        Diary diary = diaryService.createDiary(diaryRequestDto, user);
        return diary;
    }

    // 게시글 수정
    @PutMapping("/api/diary/{id}")
    public Long updateDiary(
            @PathVariable Long id,
            @RequestBody DiaryRequestDto requestDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails)
    {
        diaryService.updateDiary(id, requestDto, principalDetails);
        return id;
    }


    //게시글 삭제
    @DeleteMapping("/api/diary/{id}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        diaryService.deleteDiary(commentId,principalDetails);
    }


    //마이페이지
    @GetMapping("/api/mypage/{userId}")
    public List<DiaryResponseDto > getMyDiary(){}



    }




