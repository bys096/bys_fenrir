package com.fenrir.guruguru_spring.domain.admin.controller;

import com.fenrir.guruguru_spring.domain.admin.service.AdminService;
import com.fenrir.guruguru_spring.domain.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.dto.AdminUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
