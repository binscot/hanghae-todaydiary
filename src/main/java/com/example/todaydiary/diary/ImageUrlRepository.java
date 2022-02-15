package com.example.todaydiary.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageUrlRepository extends JpaRepository <ImageUrl, Long> {
    List<ImageUrl> findAllByImageUrlById ();
}
