package com.fenrir.guruguru_spring.domain.admin.repository;

import com.fenrir.guruguru_spring.domain.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.dto.AdminUserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepositoryCustom {

    public Page<AdminUserResponseDto> getAllMember(Pageable pageable, AdminUserPaginationRequestDto dto);
}
