package com.example.todaydiary.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByDiaryIdOrderByModifiedAtDesc(Long diaryId);

    List<Comment> findAllByDiaryId(Long diaryId);
}
