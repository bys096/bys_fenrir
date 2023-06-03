package com.fenrir.guruguru_spring.domain.user.repository;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void insert() {
        User user = User.builder().userEmail("aa@aa").userName("ys").build();
        userRepository.save(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    void update() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException());
        user.update("ysupdate");
    }

    @Test
    void delete() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }


}