package com.fenrir.guruguru_spring.domain.review.controller;

import com.fenrir.guruguru_spring.domain.review.dto.ReviewCreateRequestDto;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewDuplicateException;
import com.fenrir.guruguru_spring.domain.review.service.ReviewService;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
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

    @DeleteMapping("/{rid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable("rid") Long rid) {
        System.out.println("rid");
        reviewService.deleteReview(rid);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public void getReviewAndCommentList() {

    }
}
