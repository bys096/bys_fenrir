package com.fenrir.guruguru_spring.domain.review.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {


    private String reviewText;

    @Builder
    public ReviewUpdateRequestDto(String reviewText) {
        this.reviewText = reviewText;
    }
}
