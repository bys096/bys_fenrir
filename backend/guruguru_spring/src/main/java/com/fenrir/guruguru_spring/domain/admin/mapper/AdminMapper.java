package com.fenrir.guruguru_spring.domain.admin.mapper;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.Role;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class AdminMapper {

    public User toEntity(@Valid AdminUpdateRequestDto dto, PasswordEncoder passwordEncoder) {
        return User.builder()
                .userId(dto.getUserId())
                .userNick(dto.getUserNick())
                .userRole(dto.getUserRole())
                .build();
    }
}
