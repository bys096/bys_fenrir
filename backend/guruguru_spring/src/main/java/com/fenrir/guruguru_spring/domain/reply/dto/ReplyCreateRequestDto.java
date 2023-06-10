package com.fenrir.guruguru_spring.domain.reply.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReplyCreateRequestDto {

//    @NotNull(message = "レビューIDは必須項目です。")
//    private Long rid;

    @NotEmpty(message = "テキストは必須項目です。")
    private String replyText;

    private Long reviewId;

}
