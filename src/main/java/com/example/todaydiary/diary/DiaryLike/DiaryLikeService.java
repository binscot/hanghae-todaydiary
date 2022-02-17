package com.example.todaydiary.diary.DiaryLike;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.diary.DiaryRepository;
import com.example.todaydiary.user.User;
import com.example.todaydiary.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class DiaryLikeService {
    private final DiaryLikeRepository diaryLikeRepository;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public DiaryLikeResponseDto addLike(Long diaryId, Long uid) {
        User user = userRepository.findById(uid).orElseThrow(
                () -> new IllegalArgumentException("유저 정보가 없습니다.")
        );

        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                ()->new IllegalArgumentException("일기가 없습니다.")
        );

        DiaryLike findLike = diaryLikeRepository.findByUserAndDiary(user,diary).orElse(null);

        if(findLike == null){
            DiaryLikeRequestDto requestDto = new DiaryLikeRequestDto(user, diary);
            DiaryLike diaryLike = new DiaryLike(requestDto);
            diaryLikeRepository.save(diaryLike);
        } else {
            diaryLikeRepository.deleteById(findLike.getId());
        }
        return new DiaryLikeResponseDto (diaryId, diaryLikeRepository.countByDiary(diary));
    }
}

