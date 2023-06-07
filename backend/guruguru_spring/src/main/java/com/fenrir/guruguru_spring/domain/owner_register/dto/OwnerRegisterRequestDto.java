package com.fenrir.guruguru_spring.domain.owner_register.dto;

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
public class OwnerRegisterRequestDto {

    @NotEmpty(message = "ストア名は必須項目です。")
    private String storeName;

    @NotEmpty(message = "ストアコードは必須項目です。")
    private String storeCode;


    // address, access
}
