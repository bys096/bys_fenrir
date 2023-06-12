package com.fenrir.guruguru_spring.domain.owner_register.Mapper;

import com.fenrir.guruguru_spring.domain.owner_register.dto.OwnerRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegisterFile;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class OwnerRegisterMapper {

    public OwnerRegister toEntity(@Valid User user, Store store) {
        return OwnerRegister.builder()
                .owner(user)
                .store(store)
                .orState(0)
                .build();
    }

    public OwnerRegisterFile toEntity(@Valid String fileName, OwnerRegister owner) {
        return OwnerRegisterFile.builder()
                .owner(owner)
                .orFileName(fileName)
                .build();
    }
}
