package com.fenrir.guruguru_spring.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminUserResponseDto {

    private String userEmail;

    private String userPw;

    private LocalDateTime createdAt;

    private String userNick;

    private String role;

    private String storeCode;

    private String storeName;

    public AdminUserResponseDto(String userEmail, String userPw, LocalDateTime createdAt, String userNick, String role, String storeCode, String storeName) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.createdAt = createdAt;
        this.userNick = userNick;
        this.role = role;
        this.storeCode = storeCode;
        this.storeName = storeName;
    }
}
