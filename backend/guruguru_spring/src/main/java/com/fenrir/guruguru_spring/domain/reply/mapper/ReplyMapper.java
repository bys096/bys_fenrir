package com.fenrir.guruguru_spring.domain.reply.mapper;

import com.fenrir.guruguru_spring.domain.reply.dto.ReplyCreateRequestDto;
import com.fenrir.guruguru_spring.domain.reply.entity.Reply;
import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {

    public Reply toEntity(ReplyCreateRequestDto dto, Review review, User user) {
        return Reply.builder()
                .review(review)
//                .user(user)
                .replyText(dto.getReplyText())
                .build();
    }
}
