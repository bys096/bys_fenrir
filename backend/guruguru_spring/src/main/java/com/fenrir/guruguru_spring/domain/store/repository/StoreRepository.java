package com.fenrir.guruguru_spring.domain.store.repository;

import com.fenrir.guruguru_spring.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Optional<Store> findByStoreCode(String storeCode);
}
