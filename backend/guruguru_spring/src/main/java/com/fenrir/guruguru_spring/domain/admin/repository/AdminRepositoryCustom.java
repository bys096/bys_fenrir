package com.fenrir.guruguru_spring.domain.admin.repository;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminOwnerResponseDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepositoryCustom {

    Page<AdminUserResponseDto> getAllMember(Pageable pageable, AdminUserPaginationRequestDto dto);

    Page<AdminOwnerResponseDto> getAllOwner(Pageable pageable, AdminUserPaginationRequestDto dto);
}
