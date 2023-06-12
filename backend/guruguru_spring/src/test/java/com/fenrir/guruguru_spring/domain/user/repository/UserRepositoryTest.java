package com.fenrir.guruguru_spring.domain.user.repository;

import com.fenrir.guruguru_spring.domain.user.entity.Role;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void insert() {
        User user = User.builder().userEmail("aa@aa").userName("ys").build();
        userRepository.save(user);
    }

//    @Test
//    @Transactional
//    @Rollback(false)
//    void update() {
//        User user = userRepository.findById(1L)
//                .orElseThrow(() -> new UserNotFoundException());
//        user.updateUser("ysupdate");
//    }

    @Test
    void delete() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }

    @Test
    void createUser() {
        IntStream.rangeClosed(10, 50).forEach(i -> {
            Role role = Role.USER;
            User user = User.builder()
                    .userEmail("test" + i + "@naver.com")
                    .userPw(passwordEncoder.encode("1111"))
                    .userNick("test" + i)
                    .userRole(role)
                    .build();
            userRepository.save(user);
        });
    }


}