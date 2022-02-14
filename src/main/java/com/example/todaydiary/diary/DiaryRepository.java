package com.example.todaydiary.diary;

import com.example.todaydiary.diary.Diary;
import jdk.javadoc.internal.doclets.formats.html.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
        List<Diary> findAllByOrderByCreatedAtDesc();


}

