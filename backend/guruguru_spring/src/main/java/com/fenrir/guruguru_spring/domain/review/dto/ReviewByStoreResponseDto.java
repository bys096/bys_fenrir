package com.fenrir.guruguru_spring.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewByStoreResponseDto {

    private Long userId;

    private String nickName;

//    private String imageUrl;

    private Long reviewId;

    private LocalDateTime createdAt;

    private Integer reviewRating;

    // iine

    private String reviewText;

    @Builder
    public ReviewByStoreResponseDto(String nickName, LocalDateTime createdDate,
        Integer reviewRating, String reviewText, Long userId, Long reviewId) {
        this.nickName = nickName;
        this.createdAt = createdDate;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
        this.userId = userId;
        this.reviewId = reviewId;
    }
}


