package com.jinn.board_with_bcrypt.service;

import com.jinn.board_with_bcrypt.dto.CommentRequestDto;
import com.jinn.board_with_bcrypt.dto.CommentResponseDto;
import com.jinn.board_with_bcrypt.model.Comment;
import com.jinn.board_with_bcrypt.model.Post;
import com.jinn.board_with_bcrypt.model.User;
import com.jinn.board_with_bcrypt.repository.CommentRepository;
import com.jinn.board_with_bcrypt.repository.PostRepository;
import com.jinn.board_with_bcrypt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public CommentResponseDto create(Long postId, CommentRequestDto dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post를 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(dto.getContent());

        return CommentResponseDto.fromEntity(commentRepository.save(comment));
    }
}