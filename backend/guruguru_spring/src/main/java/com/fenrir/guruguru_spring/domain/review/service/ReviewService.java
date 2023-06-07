package com.fenrir.guruguru_spring.domain.review.service;

import com.fenrir.guruguru_spring.domain.review.dto.ReviewByStoreResponseDto;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewCreateRequestDto;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewDuplicateException;
import com.fenrir.guruguru_spring.domain.review.mapper.ReviewMapper;
import com.fenrir.guruguru_spring.domain.review.repository.ReviewRepository;
import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import com.fenrir.guruguru_spring.domain.owner_register.exception.StoreNotFoundException;
import com.fenrir.guruguru_spring.domain.owner_register.repository.OwnerRegisterRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
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
        storeRepository.findByStoreCode(dto.getStoreCode())
                .ifPresentOrElse(
                        store -> {
                            reviewRepository.findByUser_UserId(SecurityUtil.getCurrentMemberId())
                                    .ifPresentOrElse(
                                            isReview -> {
                                                throw new ReviewDuplicateException();
                                            },
                                            () -> {
                                                reviewRepository.save(reviewMapper.toEntity(dto, user, store));
                                            }
                                    );
                        },
                        () -> {
                            Store savedStore =  storeRepository.save(storeMapper.toEntity(dto.getStoreCode(), dto.getStoreName()));
                            reviewRepository.save(reviewMapper.toEntity(dto, user, savedStore));
                        }
                );
    }

    public Page<ReviewByStoreResponseDto> getAllReviewByStore(String storeCode, ReviewPaginationRequestDto requestDto) {

        Pageable pageable = PageRequest.of(requestDto.getPage(), requestDto.getLimit());

        return reviewRepositoryCustom.getReviewByStore(storeCode, pageable, requestDto);
    }

    public void deleteReview(Long rid) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new UserNotFoundException());

        reviewRepository.findById(rid)
                .orElseThrow(() -> new ReviewDuplicateException());

        reviewRepository.deleteById(rid);
    }

}
