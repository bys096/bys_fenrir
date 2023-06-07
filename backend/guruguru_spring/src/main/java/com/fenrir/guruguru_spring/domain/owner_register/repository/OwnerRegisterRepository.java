package com.fenrir.guruguru_spring.domain.owner_register.repository;

import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRegisterRepository extends JpaRepository<OwnerRegister, Long> {

//    Optional<OwnerRegister> findByStoreCode(String storeCode);
}
