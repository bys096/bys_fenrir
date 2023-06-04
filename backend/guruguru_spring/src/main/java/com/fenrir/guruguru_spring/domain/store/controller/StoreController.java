package com.fenrir.guruguru_spring.domain.store.controller;

import com.fenrir.guruguru_spring.domain.store.dto.StoreRegisterRequestDto;
import com.fenrir.guruguru_spring.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

//    自分の店を登録
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void registerStore(@Valid @RequestBody StoreRegisterRequestDto dto) {
        storeService.registerStore(dto);
    }

    @DeleteMapping("/{uid}/{sid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable("uid") Long uid, @PathVariable("sid") Long sid) {
        storeService.deleteStore(uid, sid);
    }

}
