package com.fenrir.guruguru_spring.domain.reply.dto;

import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReplyCreateRequestDto {

    @NotNull(message = "レビューIDは必須項目です。")
    private Long rid;

    @NotEmpty(message = "テキストは必須項目です。")
    private String replyText;
}
