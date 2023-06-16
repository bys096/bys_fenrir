package com.fenrir.guruguru_spring.domain.user.service;

import com.fenrir.guruguru_spring.domain.user.dto.LoginRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserCreateRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.mapper.UserMapper;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.security.jwt.RefreshToken;
import com.fenrir.guruguru_spring.global.security.jwt.RefreshTokenRepository;
import com.fenrir.guruguru_spring.global.security.jwt.TokenDto;
import com.fenrir.guruguru_spring.global.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public void createUser(UserCreateRequestDto dto) {
        userRepository.save(userMapper.toEntity(dto, passwordEncoder));
    }

    @Transactional
    public void updateUser(UserUpdateRequestDto dto, Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException());
        user.updateUser(dto);
    }

    @Transactional
    public void deleteUser(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.deleteById(uid);
    }

    @Transactional
    public TokenDto login(LoginRequestDto dto) throws BusinessException {

        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        List<?> l =  authentication.getAuthorities().stream().collect(Collectors.toList());
        User user = userRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication, user.getUserId());
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }

}
