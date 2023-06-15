package com.fenrir.guruguru_spring.domain.admin.dto;

import com.fenrir.guruguru_spring.domain.user.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class AdminUpdateRequestDto {

    @NotNull
    private Long userId;

    @NotBlank
    private String userNick;

    @NotBlank
    private String userName;

    private Role userRole;

    private String userPw;

    @Builder
    public AdminUpdateRequestDto(Long userId, String userNick, Role userRole, String userName) {
        this.userId = userId;
        this.userNick = userNick;
        this.userName = userName;
        this.userRole = userRole;
    }
}
