package com.fenrir.guruguru_spring.domain.user.controller;

import com.fenrir.guruguru_spring.domain.user.dto.LoginRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserCreateRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
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
    public ResponseEntity<User> login(@Valid @RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
