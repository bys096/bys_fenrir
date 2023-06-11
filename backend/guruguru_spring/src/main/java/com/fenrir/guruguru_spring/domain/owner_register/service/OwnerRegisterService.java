package com.fenrir.guruguru_spring.domain.owner_register.service;

import com.fenrir.guruguru_spring.domain.owner_register.Mapper.OwnerRegisterMapper;
import com.fenrir.guruguru_spring.domain.owner_register.dto.OwnerRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import com.fenrir.guruguru_spring.domain.owner_register.exception.OwnerDuplicateException;
import com.fenrir.guruguru_spring.domain.owner_register.repository.OwnerRegisterFileRepository;
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

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerRegisterService {

    private final OwnerRegisterRepository ownerRegisterRepository;
    private final OwnerRegisterFileRepository ownerRegisterFileRepository;
    private final UserRepository userRepository;
    private final OwnerRegisterMapper ownerRegisterMapper;
    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;

    public void registerStore(OwnerRegisterRequestDto dto) throws BusinessException {

        log.info("register store service 진입");
        log.info(dto.toString());

        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                        .orElseThrow(() -> {
                            throw new UserNotFoundException();
                        });
        Optional<OwnerRegister> ownerRegister = ownerRegisterRepository.findByStore_StoreCode(dto.getStoreCode());
        ownerRegister.ifPresent(o -> {
            throw new OwnerDuplicateException();
        });


        log.info("가게 중복검사 완료");
        storeRepository.findByStoreCode(dto.getStoreCode())
                        .ifPresentOrElse(
                                store -> {
                                    log.info("owner 등록 시작");
                                    OwnerRegister owner = ownerRegisterRepository.save(ownerRegisterMapper.toEntity(user, store));
                                    log.info("owner 파일등록 시작");
                                    ownerRegisterFileRepository.save(ownerRegisterMapper.toEntity(dto.getFileName(), owner));
                                },
                                () -> {
                                    Store store = storeRepository.save(storeMapper.toEntity(dto.getStoreCode(), dto.getStoreName()));
                                    log.info("실행됨?");
                                    log.info(store.getStoreName());

                                    OwnerRegister owner =  ownerRegisterRepository.save(ownerRegisterMapper.toEntity(user, store));
                                    ownerRegisterFileRepository.save(ownerRegisterMapper.toEntity(dto.getFileName(), owner));
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
