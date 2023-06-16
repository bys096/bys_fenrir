package com.fenrir.guruguru_spring.domain.user.controller;

import com.fenrir.guruguru_spring.domain.user.dto.LoginRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserCreateRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.service.UserService;
import com.fenrir.guruguru_spring.global.security.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@Valid @RequestBody UserCreateRequestDto dto) {
        userService.createUser(dto);
    }

    @PatchMapping("/{uid}")
    @ResponseStatus(HttpStatus.OK)
    public void userUpdate(@Valid @RequestBody UserUpdateRequestDto dto, @PathVariable("uid") Long uid) {
        userService.updateUser(dto, uid);
    }

    @DeleteMapping("/{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userDelete(@Valid @PathVariable("uid") Long uid) {
        userService.deleteUser(uid);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequestDto dto, HttpServletResponse response) {

        TokenDto tokenDTO = userService.login(dto);
        Cookie cookie = new Cookie("token", tokenDTO.getRefreshToken());
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("Authorization", "Bearer " + tokenDTO.getAccessToken());

        return ResponseEntity.ok(tokenDTO);
    }
}
