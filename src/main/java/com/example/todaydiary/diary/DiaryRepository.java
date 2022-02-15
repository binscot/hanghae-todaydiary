package com.example.todaydiary.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
        List<Diary> findAllByOrderByCreatedAtDesc();



}

