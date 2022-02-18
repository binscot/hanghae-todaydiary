package com.example.todaydiary.diary.DiaryLike;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, Long> {
    Optional<DiaryLike> findByUserAndDiary(User user, Diary diary);

    Long countByDiary(Diary diary);

    Long deleteByDiary(Diary diary);
}