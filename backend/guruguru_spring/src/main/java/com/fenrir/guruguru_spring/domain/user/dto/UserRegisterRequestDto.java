package com.fenrir.guruguru_spring.domain.user.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {

    @NotEmpty(message = "ニックネームは必須項目です。")
    private String nickName;

    @NotEmpty(message = "パスワードは必須項目です。")
    private String pw;

    @NotEmpty(message = "E-mailは必須項目です。")
    private String email;

    @NotEmpty(message = "ユーザー名は必須項目です。")
    private String userName;



}
