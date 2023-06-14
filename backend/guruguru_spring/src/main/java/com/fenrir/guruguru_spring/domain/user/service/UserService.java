package com.fenrir.guruguru_spring.domain.user.service;

import com.fenrir.guruguru_spring.domain.user.dto.LoginRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserCreateRequestDto;
import com.fenrir.guruguru_spring.domain.user.dto.UserUpdateRequestDto;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.mapper.UserMapper;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
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
    public TokenDto login(LoginRequestDto dto) {

        UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        List<?> l =  authentication.getAuthorities().stream().collect(Collectors.toList());
        l.forEach(i -> System.out.println(i.toString()));
//        log.info(authentication.getAuthorities().stream().collect(Collectors.toList()));

        User user = userRepository.findById(Long.parseLong(authentication.getName()))
                .orElseThrow(() -> new UserNotFoundException());


        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication, user.getUserId());
        log.info("accessToken:" + tokenDto.getAccessToken());
        log.info("userId: " + tokenDto.getUid());

        log.info("tokentDto 발급");
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        log.info("refresh before");
        refreshTokenRepository.save(refreshToken);
        log.info("refresh after");
        // 5. 토큰 발급
        return tokenDto;
    }

}
