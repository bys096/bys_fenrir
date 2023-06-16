package com.fenrir.guruguru_spring.domain.review.controller;

import com.fenrir.guruguru_spring.domain.review.dto.*;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewDuplicateException;
import com.fenrir.guruguru_spring.domain.review.service.ReviewService;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@Valid @RequestBody ReviewCreateRequestDto dto) {
        reviewService.createReview(dto);
    }


    @GetMapping("/list/{storeCode}")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReviewByStoreWithReplyDto> getAllReviewByStore(@PathVariable("storeCode") String storeCode,
                @ModelAttribute ReviewPaginationRequestDto requestDto) {
        return reviewService.getAllReviewByStore(storeCode, requestDto);
    }



    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId, null);
    }

    @DeleteMapping("/{reviewId}/{replyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReviewWithReply(@PathVariable("reviewId") Long reviewId, @PathVariable("replyId") Long replyId) {
        reviewService.deleteReview(reviewId, replyId);
    }


    @PatchMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@PathVariable("reviewId") Long reviewId, @Valid @RequestBody ReviewUpdateRequestDto dto) {
        reviewService.updateReview(reviewId, dto);
    }



}
