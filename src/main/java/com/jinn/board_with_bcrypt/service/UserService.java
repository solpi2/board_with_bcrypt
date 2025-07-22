package com.jinn.board_with_bcrypt.service;

import com.jinn.board_with_bcrypt.dto.UserRequestDto;
import com.jinn.board_with_bcrypt.dto.UserResponseDto;
import com.jinn.board_with_bcrypt.model.User;
import com.jinn.board_with_bcrypt.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;


    //중복된 ID 에러처리
    public UserResponseDto register(UserRequestDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("이미 등록된 회원입니다.");
        }

        String hash = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10));
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(hash); // 암호화 저장

        return UserResponseDto.fromEntity(userRepository.save(user)); // 레포지토리에 저장
    }

    public boolean login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));

        if(!BCrypt.checkpw(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("비밀번호가 다릅니다.");
        }

        return true;
    }
}
