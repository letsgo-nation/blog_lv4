package com.thesun4sky.springblog.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thesun4sky.springblog.dto.AuthRequestDto;
import com.thesun4sky.springblog.entity.User;
import com.thesun4sky.springblog.entity.UserRoleEnum;
import com.thesun4sky.springblog.jwt.JwtUtil;
import com.thesun4sky.springblog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void signup(AuthRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        UserRoleEnum role = requestDto.getRole();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    //  Username이 이미 존재하는지 확인한다. 중복체크
        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public void login(AuthRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        //사용자 확인 (username 이 없는 경우)
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //비밀번호 확인 (password 가 다른 경우)
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 사용자가 입력한 Password와 인코딩한 상태에서의 비밀번호를 비교해주는 메서드
    }
}
