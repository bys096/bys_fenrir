package com.fenrir.guruguru_spring.domain.admin.controller;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminOwnerResponseDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.admin.service.AdminService;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

@RestController
@Slf4j
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

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId) {
        adminService.deleteUser(userId);
    }

    @PatchMapping("/owner/accept/{orId}")
    @ResponseStatus(HttpStatus.OK)
    public void acceptOwner(@PathVariable("orId") Long orId) {
        log.info("or Id: " + orId);
        adminService.acceptOwner(orId);
    }

    @DeleteMapping("/owner/reject/{orId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rejectOwner(@PathVariable("orId") Long orId) {
        log.info("reject or Id: " + orId);
        adminService.rejectOwner(orId);
    }
}
