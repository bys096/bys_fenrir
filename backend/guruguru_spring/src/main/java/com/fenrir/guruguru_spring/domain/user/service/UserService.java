package com.fenrir.guruguru_spring.domain.user.service;

import com.fenrir.guruguru_spring.domain.user.dto.UserRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.mapper.UserMapper;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void userRegister(UserRegisterRequestDto dto) {
        userRepository.save(userMapper.toEntity(dto));
    }
}
