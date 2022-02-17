package com.example.todaydiary.diary.DiaryLike;

import com.example.todaydiary.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DiaryLikeController {

    private final DiaryLikeService diaryLikeService;

    @PostMapping("api/like/{diaryId}")
    public DiaryLikeResponseDto diaryLike(@PathVariable Long diaryId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return diaryLikeService.addLike(diaryId, userDetails.getUser().getId());
    }
}
