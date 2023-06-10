package com.fenrir.guruguru_spring.domain.review.dto;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewCreateRequestDto {

    @NotNull(message = "レヴュー点数は必須項目です。")
    private int reviewRating;

    @NotEmpty(message = "テキストは必須項目です。")
    private String reviewText;

    @NotEmpty(message = "ストアコードは必須項目です。")
    private String storeCode;

    @NotEmpty(message = "ストアコードは必須項目です。")
    private String storeName;
//    @NotNull(message = "sidは必須項目です。")
//    private Long sid;


//    @NotNull(message = "uidは必須項目です。")
//    private Long uid;
}
