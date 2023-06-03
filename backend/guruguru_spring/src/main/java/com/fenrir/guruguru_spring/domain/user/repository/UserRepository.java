package com.fenrir.guruguru_spring.domain.user.repository;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserEmailAndUserPw(String userId, String userPw);
}
