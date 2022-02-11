package com.example.todaydiary.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @PostMapping("/api/comment/{diaryId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Long diaryId,
            @Validated @RequestBody CommentRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        Comment comment = commentService.cerateComment(diaryId,requestDto,principalDetails,bindingResult);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/api/comment/{diaryId}")
    public List<Comment> getComments(@PathVariable Long diaryId){
        List<Comment> comments = commentService.getComment(diaryId);
        return comments;
    }

    @PutMapping("/api/comment/{commentId}")
    public Comment updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        Comment comment = commentService.updateComment(commentId,requestDto,principalDetails);
        return comment;
    }

    @DeleteMapping("/api/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentId,userDetails);
    }


}
