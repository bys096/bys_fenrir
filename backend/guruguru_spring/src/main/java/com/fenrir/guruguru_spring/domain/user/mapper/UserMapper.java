package com.fenrir.guruguru_spring.domain.user.mapper;

import com.fenrir.guruguru_spring.domain.user.dto.UserCreateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.Role;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserMapper {
    public User toEntity(@Valid UserCreateRequestDto dto) {
        return User.builder()
                .userEmail(dto.getEmail())
                .userPw(dto.getPw())
                .userName(dto.getUserName())
                .userNick(dto.getNickName())
                .userRole(Role.USER)
                .build();
    }
}
