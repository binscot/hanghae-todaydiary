package com.example.todaydiary.diary;

import com.example.todaydiary.comment.Comment;
import com.example.todaydiary.comment.CommentRepository;
import com.example.todaydiary.security.UserDetailsImpl;
import com.example.todaydiary.user.User;
import com.example.todaydiary.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final ImageUrlRepository imageUrlRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    //글 작성
    @Transactional
    public Diary createDiary(
            DiaryRequestDto requestDto,
            User user) {

        List<ImageUrl> imageUrlList1 = new ArrayList<>();
        for (ImageUrl imageUrls : requestDto.getImageUrlList()) {
            imageUrlList1.add(imageUrlRepository.save(imageUrls)
            );
        }

        String content = requestDto.getContent();
        if (requestDto.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if (content.length() > 1000) {
            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
        }

        Diary diary = new Diary(requestDto, user, imageUrlList1);

        return diaryRepository.save(diary);
    }


    //수정
    @Transactional
    public Diary updateDiary(
            Long id,
            DiaryRequestDto requestDto,
            UserDetailsImpl userDetails) {
        Diary diary = diaryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
        User user = diary.getUser();
        if (userDetails.getUser() != user) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        if (requestDto.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력해주세요.");
        }
        if (requestDto.getContent().length() > 1000) {
            throw new IllegalArgumentException("1000자 이하로 입력해주세요.");
        }
        diary.updateDiary(requestDto);
        diaryRepository.save(diary);
        return diary;
    }


    //삭제
    @Transactional
    public Long deleteDiary(Long diaryId, UserDetailsImpl userDetails) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new IllegalArgumentException("일기가 존재하지 않습니다.")
        );
        User user = diary.getUser();
        Long deleteId = user.getId();
        if (!Objects.equals(userDetails.getUser().getId(), deleteId)) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        List<Comment> comments = commentRepository.findAllByDiaryId(diaryId);
        for (Comment comment : comments) {
            commentRepository.deleteById(comment.getId());
        }
        diaryRepository.deleteById(diaryId);
        return diaryId;
        }


}
