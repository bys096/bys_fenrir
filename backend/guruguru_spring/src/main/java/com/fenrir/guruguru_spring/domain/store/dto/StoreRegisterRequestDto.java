package com.fenrir.guruguru_spring.domain.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequestDto {

    @NotNull(message = "ユーザーidは必須項目です。")
    private Long uid;

//    @NotEmpty(message = "ストア名は必須項目です。")
//    private String storeName;

    @NotEmpty(message = "ストアコードは必須項目です。")
    private String storeCode;

    // address, access
}
