package com.fenrir.guruguru_spring.domain.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fenrir.guruguru_spring.domain.user.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class AdminUserResponseDto {

    private Long userId;

    private String userEmail;

    private String userPw;

    private String userNick;

    private String userName;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    private Role userRole;

    @Builder
    public AdminUserResponseDto(Long userId, String userEmail, String userPw, LocalDateTime createdAt, String userNick, Role userRole, String userName) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.createdAt = createdAt;
        this.userNick = userNick;
        this.userRole = userRole;
        this.userName = userName;
        this.userId = userId;
    }
}
