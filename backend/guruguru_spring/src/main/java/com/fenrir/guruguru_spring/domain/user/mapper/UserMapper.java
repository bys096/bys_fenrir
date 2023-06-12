package com.fenrir.guruguru_spring.domain.user.mapper;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserCreateRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.Role;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class UserMapper {
    public User toEntity(@Valid UserCreateRequestDto dto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .userEmail(dto.getEmail())
                .userPw(passwordEncoder.encode(dto.getPw()))
                .userName(dto.getUserName())
                .userNick(dto.getNickName())
                .userRole(Role.USER)
                .build();
    }

}
