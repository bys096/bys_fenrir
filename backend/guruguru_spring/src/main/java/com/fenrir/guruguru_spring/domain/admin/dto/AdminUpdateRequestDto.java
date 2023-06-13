package com.fenrir.guruguru_spring.domain.admin.dto;

import com.fenrir.guruguru_spring.domain.user.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AdminUpdateRequestDto {

    @NotNull
    private Long userId;

    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPw;

    @NotBlank
    private String userName;

    @NotBlank
    private String userNick;

    private Role userRole;

    @Builder
    public AdminUpdateRequestDto(Long userId, String userEmail, String userPw, String userName, String userNick, Role userRole) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.userName = userName;
        this.userNick = userNick;
        this.userRole = userRole;
    }
}
