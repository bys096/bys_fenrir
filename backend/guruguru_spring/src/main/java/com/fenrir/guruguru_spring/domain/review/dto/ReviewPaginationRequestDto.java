package com.fenrir.guruguru_spring.domain.review.dto;

import com.fenrir.guruguru_spring.global.common.Sort;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
public class ReviewPaginationRequestDto {

    @PositiveOrZero
    private Integer page;

    @Positive
    private Integer limit;

    private Sort sort;

    @Builder
    public ReviewPaginationRequestDto(Integer page, Integer limit, Sort sort) {
        this.page = page == null ? 0 : page;
        this.limit = limit == null ? 4 : limit;
        this.sort = sort == null ? Sort.CREATED_DATE_DESC : sort;
    }
}
