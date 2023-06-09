package com.fenrir.guruguru_spring.domain.review.dto;

import com.fenrir.guruguru_spring.domain.reply.dto.ReplyResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewByStoreWithReplyDto {

    private ReviewByStoreResponseDto review;
    private ReplyResponseDto reply;

    @Builder
    public ReviewByStoreWithReplyDto(ReviewByStoreResponseDto review, ReplyResponseDto reply) {
        this.review = review;
        this.reply = reply;
    }
}
