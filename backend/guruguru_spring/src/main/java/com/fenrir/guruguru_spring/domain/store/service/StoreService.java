package com.fenrir.guruguru_spring.domain.store.service;

import com.fenrir.guruguru_spring.domain.store.Mapper.StoreMapper;
import com.fenrir.guruguru_spring.domain.store.dto.StoreRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.store.exception.StoreNotFoundException;
import com.fenrir.guruguru_spring.domain.store.repository.StoreRepository;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreMapper storeMapper;

    public void registerStore(StoreRegisterRequestDto dto) {
        User user =  userRepository.findById(dto.getUid())
                .orElseThrow(() -> new UserNotFoundException());

        storeRepository.save(storeMapper.toEntity(dto, user));
    }

    public void deleteStore(Long uid, Long sid) {
        userRepository.findById(uid)
                .orElseThrow(() -> new UserNotFoundException());

        Store store = storeRepository.findById(sid)
                .orElseThrow(() -> new StoreNotFoundException());

        storeRepository.deleteById(store.getStoreId());
    }
}
