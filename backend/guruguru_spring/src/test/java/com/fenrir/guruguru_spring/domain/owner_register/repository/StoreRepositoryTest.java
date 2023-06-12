package com.fenrir.guruguru_spring.domain.owner_register.repository;

import com.amazonaws.services.s3.model.Owner;
import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.store.repository.StoreRepository;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    OwnerRegisterRepository ownerRegisterRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Test
    void createOwner() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            User user = userRepository.findById(1L).get();
            Store store = storeRepository.findByStoreCode("J000126497").get();
            OwnerRegister own = OwnerRegister.builder()
                    .owner(user)
                    .store(store)
                    .build();
            ownerRegisterRepository.save(own);
        });
    }
}