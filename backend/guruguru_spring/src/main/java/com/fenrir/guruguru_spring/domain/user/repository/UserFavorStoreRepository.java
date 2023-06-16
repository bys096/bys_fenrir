package com.fenrir.guruguru_spring.domain.user.repository;

import com.fenrir.guruguru_spring.domain.user.entity.UserFavorStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavorStoreRepository extends JpaRepository<UserFavorStore, Long> {
}
