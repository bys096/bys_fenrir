package com.fenrir.guruguru_spring.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReplyResponseDto {

    private Long replyId;
    private Long rid;
    private String replyText;
    private Long userId;
    private String userNick;
    private LocalDateTime createdAt;

    @Builder
    public ReplyResponseDto(Long replyId, Long rid, String replyText, Long userId,
            String userNick, LocalDateTime createdAt) {
        this.replyId = replyId;
        this.rid = rid;
        this.replyText = replyText;
        this.userId = userId;
        this.userNick = userNick;
        this.createdAt = createdAt;
    }
}
