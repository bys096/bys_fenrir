package com.fenrir.guruguru_spring.domain.review.mapper;

import com.fenrir.guruguru_spring.domain.review.dto.ReviewCreateRequestDto;
import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review toEntity(ReviewCreateRequestDto dto, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .reviewText(dto.getReviewText())
                .reviewRating(dto.getReviewRating())
                .build();
    }
}
