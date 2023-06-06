package com.fenrir.guruguru_spring.domain.review.dto;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequestDto {

    @NotNull(message = "レヴュー点数は必須項目です。")
    private int reviewRating;

    @NotEmpty(message = "テキストは必須項目です。")
    private String reviewText;

    @NotNull(message = "sidは必須項目です。")
    private Long sid;

//    @NotNull(message = "uidは必須項目です。")
//    private Long uid;
}
