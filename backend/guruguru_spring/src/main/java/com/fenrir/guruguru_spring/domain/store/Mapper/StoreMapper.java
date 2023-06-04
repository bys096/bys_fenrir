package com.fenrir.guruguru_spring.domain.store.Mapper;

import com.fenrir.guruguru_spring.domain.store.dto.StoreRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class StoreMapper {

    public Store toEntity(@Valid StoreRegisterRequestDto dto, User user) {
        return Store.builder()
                .owner(user)
                .storeName(dto.getStoreName())
                .storeCode(dto.getStoreCode())
                .build();
    }
}
