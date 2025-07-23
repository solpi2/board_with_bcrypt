package com.jinn.board_with_bcrypt.controller;


import com.jinn.board_with_bcrypt.dto.CommentRequestDto;
import com.jinn.board_with_bcrypt.dto.CommentResponseDto;
import com.jinn.board_with_bcrypt.model.Comment;
import com.jinn.board_with_bcrypt.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping
    public CommentResponseDto create(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto dto
    ) {
        return commentService.create(postId, dto);
    }
}