package com.fenrir.guruguru_spring.domain.review;

import com.fenrir.guruguru_spring.domain.review.dto.ReviewCreateRequestDto;
import com.fenrir.guruguru_spring.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@Valid @RequestBody ReviewCreateRequestDto dto) {
        reviewService.createReview(dto);
    }
}
