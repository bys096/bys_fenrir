package com.fenrir.guruguru_spring.domain.review.service;

import com.fenrir.guruguru_spring.domain.reply.entity.Reply;
import com.fenrir.guruguru_spring.domain.reply.exception.ReplyNotFoundException;
import com.fenrir.guruguru_spring.domain.reply.repository.ReplyRepository;
import com.fenrir.guruguru_spring.domain.review.dto.*;
import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewDuplicateException;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewNotFoundException;
import com.fenrir.guruguru_spring.domain.review.mapper.ReviewMapper;
import com.fenrir.guruguru_spring.domain.review.repository.ReviewRepository;
import com.fenrir.guruguru_spring.domain.review.repository.ReviewRepositoryCustom;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.store.mapper.StoreMapper;
import com.fenrir.guruguru_spring.domain.store.repository.StoreRepository;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewMapper reviewMapper;
    private final StoreMapper storeMapper;
    private final ReviewRepositoryCustom reviewRepositoryCustom;


    public void createReview(ReviewCreateRequestDto dto) throws BusinessException {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });
/*
        ストア情報が登録されていたら
                自分が書いたレビューがあるのかCHECK
                    自分が書いたレビューがあれば、EXCEPTION処理
                    自分が書いたレビューがなければ、REQUEST情報をもとにレビューを登録
        ストア情報が登録されていなければ
                新しいストア情報を登録 （新しいストアだっていうことは、自分が書くレビューか初めてのレビューであることを意味する）
                REQUEST情報をもとにレビューを登録
        店主は作成ができないように変える必要がある

*/
        log.info("로그인된 유저id " + user.getUserId());
        storeRepository.findByStoreCode(dto.getStoreCode())
                .ifPresentOrElse(
                        store -> {
                            reviewRepository.getReviewByUserId(user.getUserId(), store.getStoreCode())
                                    .ifPresentOrElse(
                                            isReview -> {
                                                log.info("리뷰 중복 에러 처리 진입");
                                                System.out.println("로그안찍히네");
                                                throw new ReviewDuplicateException();
                                            },
                                            () -> {
                                                log.info("에러 처리 제대로 안됨");
                                                reviewRepository.save(reviewMapper.toEntity(dto, user, store));
                                            }
                                    );
                        },
                        () -> {
                            log.info("여긴가???");
                            Store savedStore = storeRepository.save(storeMapper.toEntity(dto.getStoreCode(), dto.getStoreName()));
                            log.info("저장됨?");
                            reviewRepository.save(reviewMapper.toEntity(dto, user, savedStore));
                        }
                );
    }

    public Page<ReviewByStoreWithReplyDto> getAllReviewByStore(String storeCode, ReviewPaginationRequestDto requestDto) {

        Pageable pageable = PageRequest.of(requestDto.getPage(), requestDto.getLimit());
        return reviewRepositoryCustom.getReviewByStore(storeCode, pageable, requestDto);
    }

    @Transactional
    public void deleteReview(Long reviewId, Long replyId) throws BusinessException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    throw new ReviewNotFoundException();
                });

        if(replyId == null) {
            reviewRepository.delete(review);
        } else {
            Reply reply = replyRepository.findById(replyId)
                    .orElseThrow(() -> {
                       throw new ReplyNotFoundException();
                    });
            replyRepository.delete(reply);
            reviewRepository.delete(review);
        }
    }

    @Transactional
    public void updateReview(Long reviewId, ReviewUpdateRequestDto dto) throws BusinessException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> {
                    throw new ReviewNotFoundException();
                });
        review.updateReview(dto.getReviewText());
    }


}
