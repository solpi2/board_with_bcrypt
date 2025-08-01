package com.jinn.board_with_bcrypt.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank
    private  String username;

    @NotBlank
    private  String password;
}
