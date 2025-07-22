package com.jinn.board_with_bcrypt.dto;

import com.jinn.board_with_bcrypt.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private String title;

    private String content;

    public static PostResponseDto fromEntity(Post post) {
        return new PostResponseDto(
                post.getTitle(),
                post.getContent()
        );
    }
}