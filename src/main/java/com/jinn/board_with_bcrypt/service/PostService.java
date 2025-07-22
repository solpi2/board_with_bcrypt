package com.jinn.board_with_bcrypt.service;

import com.jinn.board_with_bcrypt.dto.PostRequestDto;
import com.jinn.board_with_bcrypt.dto.PostResponseDto;
import com.jinn.board_with_bcrypt.model.Post;
import com.jinn.board_with_bcrypt.model.User;
import com.jinn.board_with_bcrypt.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public List<PostResponseDto> listAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    public PostResponseDto getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 Post를 찾을 수 없습니다."));

        return PostResponseDto.fromEntity(post);
    }

    public PostResponseDto create(PostRequestDto dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());

        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(dto.getTitle());
        if (dto.getContent() != null && !dto.getContent().isBlank()) post.setContent(dto.getContent());

        return PostResponseDto.fromEntity(postRepository.save(post));
    }

    public void delete(Long id, String username, String rawPassword) {
        User user = userService.login(username, rawPassword);
        PostResponseDto post = getById(id);

        if(!user.getUsername().equals(post.getAuthor().getUsername())) {
            throw new RuntimeException("다른 유저의 글은 삭제할 수 없습니다.");
        }

        postRepository.deleteById(id);
    }
}