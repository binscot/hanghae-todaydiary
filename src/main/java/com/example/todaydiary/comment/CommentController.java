//package com.example.todaydiary.comment;
//
//import com.example.todaydiary.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping("/api/comment/{diaryId}")
//    public ResponseEntity<Comment> createComment(
//            @PathVariable Long diaryId,
//            @Validated @RequestBody CommentRequestDto requestDto,
//            BindingResult bindingResult,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//
//    ){
//        Comment comment = commentService.createComment(diaryId,requestDto,userDetails,bindingResult);
//        return ResponseEntity.ok(comment);
//    }
//
//    @GetMapping("/api/comment/{diaryId}")
//    public List<Comment> getComments(@PathVariable Long diaryId){
//        return commentService.getComment(diaryId);
//    }
//
//    @PutMapping("/api/comment/{commentId}")
//    public Comment updateComment(
//            @PathVariable Long commentId,
//            @RequestBody CommentRequestDto requestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails
//    ){
//        return commentService.updateComment(commentId,requestDto,userDetails);
//    }
//
//    @DeleteMapping("/api/comment/{commentId}")
//    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        commentService.deleteComment(commentId,userDetails);
//    }
//
//
//}
