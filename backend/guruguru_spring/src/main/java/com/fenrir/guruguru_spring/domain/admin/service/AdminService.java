package com.fenrir.guruguru_spring.domain.admin.service;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminOwnerResponseDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.admin.mapper.AdminMapper;
import com.fenrir.guruguru_spring.domain.admin.repository.AdminRepositoryCustom;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserResponseDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepositoryCustom adminRepositoryCustom;
    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public Page<AdminUserResponseDto> getAllMember(AdminUserPaginationRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPage(), requestDto.getLimit());
        return adminRepositoryCustom.getAllMember(pageable, requestDto);
    }

    public Page<AdminOwnerResponseDto> getAllOwner(AdminUserPaginationRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPage(), requestDto.getLimit());
        return adminRepositoryCustom.getAllOwner(pageable, requestDto);
    }

    public void updateUser(AdminUpdateRequestDto dto) throws BusinessException {
        User user =  userRepository.findById(dto.getUserId())
                .orElseThrow(() -> {
                   throw new UserNotFoundException();
                });
        userRepository.save(adminMapper.toEntity(dto, passwordEncoder));
    }
}
