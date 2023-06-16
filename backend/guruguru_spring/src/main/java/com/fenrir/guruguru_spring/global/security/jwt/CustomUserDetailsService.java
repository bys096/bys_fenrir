package com.fenrir.guruguru_spring.global.security.jwt;

import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws BusinessException {
        return userRepository.findByUserEmail(userEmail)
                .map(this::createUserDetails)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });
    }

    private UserDetails createUserDetails(com.fenrir.guruguru_spring.domain.user.entity.User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getUserRole().toString());
        log.info(grantedAuthority.toString());

        return new User(
                String.valueOf(user.getUserId()),
                user.getUserPw(),
                Collections.singleton(grantedAuthority)
        );
    }
}