package com.fenrir.guruguru_spring.domain.store.repository;

import com.fenrir.guruguru_spring.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
