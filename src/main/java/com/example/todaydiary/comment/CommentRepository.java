package com.example.todaydiary.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByDiaryIdOrderByModifiedAtDesc(Long diaryId);

    List<Comment> findAllByDiaryId(Long diaryId);
}
