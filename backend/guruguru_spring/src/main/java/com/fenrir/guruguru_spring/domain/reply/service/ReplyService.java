package com.fenrir.guruguru_spring.domain.reply.service;

import com.fenrir.guruguru_spring.domain.owner_register.exception.StoreNotFoundException;
import com.fenrir.guruguru_spring.domain.reply.dto.ReplyCreateRequestDto;
import com.fenrir.guruguru_spring.domain.reply.exception.ReplyNotMatchUserException;
import com.fenrir.guruguru_spring.domain.reply.mapper.ReplyMapper;
import com.fenrir.guruguru_spring.domain.reply.repository.ReplyRepository;
import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewNotFoundException;
import com.fenrir.guruguru_spring.domain.review.exception.ReviewNotMatchUserException;
import com.fenrir.guruguru_spring.domain.review.repository.ReviewRepository;
import com.fenrir.guruguru_spring.domain.review.service.ReviewService;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.store.repository.StoreRepository;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.exception.UserNotFoundException;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import com.fenrir.guruguru_spring.global.error.exception.BusinessException;
import com.fenrir.guruguru_spring.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;

    public void createReply(ReplyCreateRequestDto dto) throws BusinessException {

        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });

        log.info("security Utils: " + SecurityUtil.getCurrentMemberId());
        log.info(dto.toString());

        //        本人が書いたのか、店主が書いたのかの確認
        Review review = reviewRepository.findReviewsByUserIdAndStoreCodeOrOwner(dto.getReviewId(), user.getUserId())
                .orElseThrow(() -> {
                   throw new ReplyNotMatchUserException();
                });


        replyRepository.save(replyMapper.toEntity(dto, review, user));
    }
}
