package com.jinn.board_with_bcrypt.controller;


import com.jinn.board_with_bcrypt.dto.UserRequestDto;
import com.jinn.board_with_bcrypt.dto.UserResponseDto;
import com.jinn.board_with_bcrypt.model.User;
import com.jinn.board_with_bcrypt.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto dto) {
        return userService.register(dto);
    }
}
