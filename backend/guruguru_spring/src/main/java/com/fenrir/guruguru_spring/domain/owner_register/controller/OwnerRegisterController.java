package com.fenrir.guruguru_spring.domain.owner_register.controller;

import com.fenrir.guruguru_spring.domain.owner_register.dto.OwnerRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.owner_register.service.OwnerRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/or")
public class OwnerRegisterController {

    private final OwnerRegisterService storeService;

//    自分の店を登録
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void registerStore(@Valid @RequestBody OwnerRegisterRequestDto dto) {

        log.info(dto.getStoreCode());
        log.info(dto.getStoreName());
        storeService.registerStore(dto);
    }

    @DeleteMapping("/{uid}/{sid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable("uid") Long uid, @PathVariable("sid") Long sid) {
        storeService.deleteStore(uid, sid);
    }

}
