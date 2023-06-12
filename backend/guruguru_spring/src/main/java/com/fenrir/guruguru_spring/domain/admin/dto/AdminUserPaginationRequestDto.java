package com.fenrir.guruguru_spring.domain.admin.dto;

import com.fenrir.guruguru_spring.global.common.Sort;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
public class AdminUserPaginationRequestDto {


    @PositiveOrZero
    private Integer page;

    @Positive
    private Integer limit;

    private Sort sort;

    @Builder
    public AdminUserPaginationRequestDto(Integer page, Integer limit, Sort sort) {
        this.page = page == null ? 0 : page;
        this.limit = limit == null ? 10 : limit;
        this.sort = sort == null ? Sort.CREATED_DATE_DESC : sort;
    }
}
