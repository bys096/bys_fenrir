package com.fenrir.guruguru_spring.domain.owner_register.repository;

import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface OwnerRegisterRepository extends JpaRepository<OwnerRegister, Long> {
    Optional<OwnerRegister> findByStore_StoreCode(@PathVariable("storeCode") String StoreCode);
}
