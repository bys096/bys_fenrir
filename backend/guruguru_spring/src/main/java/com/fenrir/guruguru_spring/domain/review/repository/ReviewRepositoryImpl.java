package com.fenrir.guruguru_spring.domain.review.repository;

import com.fenrir.guruguru_spring.domain.reply.dto.ReplyResponseDto;
import com.fenrir.guruguru_spring.domain.reply.entity.QReply;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewByStoreRequestDto;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewByStoreResponseDto;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewByStoreWithReplyDto;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.review.entity.QReview;
import com.fenrir.guruguru_spring.domain.user.entity.QUser;
import com.fenrir.guruguru_spring.global.common.Sort;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.fenrir.guruguru_spring.domain.review.entity.QReview.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Long countQuery(QReview review, Long userId) {
        return queryFactory
                .select(review.count())
                .from(review)
                .where(eqUserId(userId))
                .fetchOne();
    }

    private Predicate eqUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        return review.user.userId.eq(userId);
    }

    @SuppressWarnings("unchecked")
    private OrderSpecifier getOrderSpecifiers(Sort sort) {
        for (Sort value : Sort.values()) {
            if (sort == value) {
                Path<Object> path = Expressions.path(Object.class, review, value.getProperty());
                return new OrderSpecifier(value.getOrder(), path);
            }
        }
        return null;
    }


    public QBean<ReviewByStoreResponseDto> reviewSelectByStore(QReview review, QUser user) {
        return Projections.fields(
                ReviewByStoreResponseDto.class,
                review.user.userNick, review.createdAt, review.reviewRating,
                review.reviewText, review.user.userId, review.rid
        );
    }

    public QBean<ReplyResponseDto> replySelectedByReview(QReply reply, QUser user) {
        return Projections.fields(
                ReplyResponseDto.class,
                reply.replyId, reply.replyText, reply.replyUser.userId,
                reply.createdAt,reply.review.rid, reply.replyUser.userNick
        );
    }

    @Override
    public Page<ReviewByStoreWithReplyDto> getReviewByStore(String storeCode, Pageable pageable,
        ReviewPaginationRequestDto requestDto) {
        QReview review = QReview.review;
        QUser user = QUser.user;
        QReply reply = QReply.reply;

        JPQLQuery<ReviewByStoreResponseDto> results = queryFactory
                .select(reviewSelectByStore(review, user))
                .from(review)
                .innerJoin(review.user, user)
                .where(review.store.storeCode.eq(storeCode))
                .orderBy(getOrderSpecifiers(requestDto.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<ReviewByStoreResponseDto> reviews = results.fetch();


        List<ReviewByStoreWithReplyDto> reviewReplyList = reviews.stream().map(r -> {
            ReplyResponseDto replyResponseDto = queryFactory
                    .select(replySelectedByReview(reply, user))
                    .from(reply)
                    .where(reply.review.rid.eq(r.getRid()))
                    .fetchOne();
            return new ReviewByStoreWithReplyDto(r, replyResponseDto);
        }).collect(Collectors.toList());

        return new PageImpl<>(reviewReplyList, pageable, countQuery(review, null));
    }
}
