package com.fenrir.guruguru_spring.domain.user.repository;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
