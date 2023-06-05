package com.fenrir.guruguru_spring.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotEmpty(message = "パスワードは必須項目です。")
    private String pw;

    @NotEmpty(message = "E-mailは必須項目です。")
    private String email;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, pw);
    }
}
