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

        log.info("로그인정보 확인");
        log.info(dto.getEmail());
        log.info(dto.getPw());
        TokenDto tokenDTO = userService.login(dto);
        log.info("서비스 종료");

        Cookie cookie = new Cookie("token", tokenDTO.getRefreshToken());        //RefreshToken을 쿠키에 저장
        cookie.setMaxAge(3600);     //초 단위 시간
        cookie.setPath("/");        //쿠기 경로 적용하기
        response.addCookie(cookie);

        response.setHeader("Authorization", "Bearer " + tokenDTO.getAccessToken());     //AccessToken을 헤더에 담아 응답



        return ResponseEntity.ok(tokenDTO);
        //return ResponseEntity.ok(authService.login(memberRequestDto));



    }
}
