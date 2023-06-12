package com.fenrir.guruguru_spring.domain.admin.dto;

import com.fenrir.guruguru_spring.domain.user.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminUserResponseDto {

    private String userEmail;

    private String userPw;

    private String userNick;

    private String userName;

    private LocalDateTime createdAt;

    private Role userRole;

    @Builder
    public AdminUserResponseDto(String userEmail, String userPw, LocalDateTime createdAt, String userNick, Role userRole, String userName) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.createdAt = createdAt;
        this.userNick = userNick;
        this.userRole = userRole;
        this.userName = userName;
    }
}
