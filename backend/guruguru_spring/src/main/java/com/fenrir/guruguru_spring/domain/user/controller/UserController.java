package com.fenrir.guruguru_spring.domain.user.controller;

import com.fenrir.guruguru_spring.domain.user.dto.UserRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Boolean> userRegister(@Valid @RequestBody UserRegisterRequestDto dto) {
        userService.userRegister(dto);
        return ResponseEntity.ok(true);
    }
}
