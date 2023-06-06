package com.fenrir.guruguru_spring.domain.review.mapper;

import com.fenrir.guruguru_spring.domain.review.dto.ReviewCreateRequestDto;
import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toEntity(ReviewCreateRequestDto dto, User user) {
        return Review.builder()
                .user(user)
                .reviewText(dto.getReviewText())
                .reviewRating(dto.getReviewRating())
                .build();
    }
}
