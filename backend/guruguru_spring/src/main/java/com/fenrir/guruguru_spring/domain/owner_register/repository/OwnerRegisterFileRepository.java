package com.fenrir.guruguru_spring.domain.owner_register.repository;

import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegisterFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface OwnerRegisterFileRepository extends JpaRepository<OwnerRegisterFile, Long> {


    @Query("SELECT f FROM OwnerRegisterFile f WHERE f.owner.orId = :orId")
    Optional<OwnerRegisterFile> getRegisterFileByOrId(Long orId);
}
