package com.jinn.board_with_bcrypt.dto;


import com.jinn.board_with_bcrypt.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @NotBlank
    private  Long id;

    @NotBlank
    private  String username;

    public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername()
        );
    }
}
