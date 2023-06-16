package com.fenrir.guruguru_spring.domain.reply.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReplyCreateRequestDto {

    @NotEmpty(message = "テキストは必須項目です。")
    private String replyText;

    @NotNull(message = "review Idは必須項目です。")
    private Long reviewId;

}
