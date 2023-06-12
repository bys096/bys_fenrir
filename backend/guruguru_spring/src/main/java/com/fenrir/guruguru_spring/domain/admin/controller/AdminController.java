package com.fenrir.guruguru_spring.domain.admin.controller;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminOwnerResponseDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.admin.service.AdminService;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/user/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<AdminUserResponseDto> getAllMember(@ModelAttribute AdminUserPaginationRequestDto requestDto) {
        return adminService.getAllMember(requestDto);
    }

    @GetMapping("/owner/register/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<AdminOwnerResponseDto> getAllOwner(@ModelAttribute AdminUserPaginationRequestDto requestDto) {
        return adminService.getAllOwner(requestDto);
    }


    @PatchMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody AdminUpdateRequestDto dto) {
        adminService.updateUser(dto);
    }
}
