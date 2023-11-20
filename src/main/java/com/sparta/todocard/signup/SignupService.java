package com.sparta.todocard.signup;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignupService {
    public final SignupRepository signupRepository;
    public final PasswordEncoder passwordEncoder;


    public void signUp(SignUpRequestDto requestDto) {
        String username = requestDto.getUserName();
        String password = passwordEncoder.encode(requestDto.getPassWord());

        // 회원 중복 확인
        Optional<User> checkUsername = signupRepository.findUserByUserName(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        User user = new User(username,password);
        signupRepository.save(user);
    }
}
