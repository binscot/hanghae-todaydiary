package com.example.todaydiary.comment;

import com.example.todaydiary.diary.Diary;
import com.example.todaydiary.diary.DiaryRepository;
import com.example.todaydiary.security.UserDetailsImpl;
import com.example.todaydiary.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final DiaryRepository diaryRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public Comment createComment(
            Long diaryId,
            CommentRequestDto requestDto,
            UserDetailsImpl userDetails,
            BindingResult bindingResult) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시글을 찾을 수 없습니다.")
        );
        User user = userDetails.getUser();

        if (bindingResult.hasErrors()){
            throw  new IllegalArgumentException(bindingResult.getFieldError().getDefaultMessage());
        } else {
            Comment comment = new Comment(requestDto,diary,user);
            return commentRepository.save(comment);
        }

    }


    public List<Comment> getComment(Long diaryId) {
        return commentRepository.findByDiaryIdOrderByModifiedAtDesc(diaryId);
    }

    @Transactional
    public Comment updateComment(
            Long commentId,
            CommentRequestDto requestDto,
            UserDetailsImpl userDetails) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
                );
        User user = commentRepository.findById(commentId).get().getUser();
        Long commentUserId = user.getId();
        Long loginUserId = userDetails.getUser().getId();
        if (!Objects.equals(commentUserId, loginUserId)){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        if (requestDto.getComment()==null){
            throw new IllegalArgumentException("댓글을 입력해주세요!");
        }
        if (requestDto.getComment().length()>30){
            throw new IllegalArgumentException("댓글은 300자 이하로 작성해주세요!!");
        }

        comment.updateComment(requestDto);

        return comment;
    }

    @Transactional
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {
        commentRepository.findById(commentId).orElseThrow(
                        () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
                );
        User user = commentRepository.findById(commentId).get().getUser();
        Long commentUserId = user.getId();
        Long loginUserId = userDetails.getUser().getId();
        if (!Objects.equals(commentUserId, loginUserId)){
            throw new IllegalArgumentException("작성자만 삭제 할 수 있습니다.");
        } else {
            commentRepository.deleteById(commentId);
        }
    }
}
