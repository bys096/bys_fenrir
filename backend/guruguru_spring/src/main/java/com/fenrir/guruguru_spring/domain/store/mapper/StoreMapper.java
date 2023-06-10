package com.fenrir.guruguru_spring.domain.store.mapper;

import com.fenrir.guruguru_spring.domain.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
public class StoreMapper {

    public Store toEntity(String storeCode, String storeName) {
        return Store.builder()
                .storeCode(storeCode)
                .storeName(storeName)
                .build();
    }

}
