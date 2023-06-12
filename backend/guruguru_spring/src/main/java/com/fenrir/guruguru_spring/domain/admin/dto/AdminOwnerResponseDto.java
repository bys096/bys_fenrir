package com.fenrir.guruguru_spring.domain.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fenrir.guruguru_spring.domain.user.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
public class AdminOwnerResponseDto {

    private String userEmail;

    private String userPw;

    private String userName;

    private String userNick;

    private String storeCode;

    private Integer orState;

    private String storeName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;


    public AdminOwnerResponseDto(String userEmail, String userPw, String userName, String userNick, String storeCode, Integer orState, String storeName, LocalDateTime createdAt) {
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userName = userName;
        this.userNick = userNick;
        this.storeCode = storeCode;
        this.orState = orState;
        this.storeName = storeName;
        this.createdAt = createdAt;
    }
}
