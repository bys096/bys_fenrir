package com.fenrir.guruguru_spring.domain.owner_register.service;

import com.fenrir.guruguru_spring.domain.owner_register.Mapper.OwnerRegisterMapper;
import com.fenrir.guruguru_spring.domain.owner_register.dto.OwnerRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.owner_register.repository.OwnerRegisterRepository;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.store.mapper.StoreMapper;
import com.fenrir.guruguru_spring.domain.store.repository.StoreRepository;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerRegisterService {

    private final OwnerRegisterRepository ownerRegisterRepository;
    private final UserRepository userRepository;
    private final OwnerRegisterMapper ownerRegisterMapper;
    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;

    public void registerStore(OwnerRegisterRequestDto dto) throws BusinessException {

        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                        .orElseThrow(() -> {
                            throw new UserNotFoundException();
                        });

        // 가게가 이미 같은 가게를 가지고 있을 경우의 예외처리 필요


        storeRepository.findByStoreCode(dto.getStoreCode())
                        .ifPresentOrElse(
                                store -> {
                                    ownerRegisterRepository.save(ownerRegisterMapper.toEntity(user, store));
                                },
                                () -> {
                                    Store store = storeRepository.save(storeMapper.toEntity(dto.getStoreCode(), dto.getStoreName()));
                                    log.info("실행됨?");
                                    log.info(store.getStoreName());

                                    ownerRegisterRepository.save(ownerRegisterMapper.toEntity(user, store));
                                }
                        );
    }

    public void deleteStore(Long uid, Long sid) {
//        userRepository.findById(uid)
//                .orElseThrow(() -> new UserNotFoundException());
//
//        OwnerRegister store = storeRepository.findById(sid)
//                .orElseThrow(() -> new StoreNotFoundException());
//
//        storeRepository.deleteById(store.getStoreId());
    }
}
