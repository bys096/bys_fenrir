package com.fenrir.guruguru_spring.domain.admin.repository;

import com.fenrir.guruguru_spring.domain.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.dto.AdminUserResponseDto;
import com.fenrir.guruguru_spring.domain.reply.dto.ReplyResponseDto;
import com.fenrir.guruguru_spring.domain.reply.entity.QReply;
import com.fenrir.guruguru_spring.domain.review.dto.ReviewByStoreResponseDto;
import com.fenrir.guruguru_spring.domain.store.entity.QStore;
import com.fenrir.guruguru_spring.domain.user.entity.QUser;
import com.fenrir.guruguru_spring.domain.user.entity.Role;
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

import static com.fenrir.guruguru_spring.domain.review.entity.QReview.review;
import static com.fenrir.guruguru_spring.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdminRepositoryImpl implements AdminRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    public Long AdminUserCountQuery(QUser user, Long userId) {
        return queryFactory
                .select(user.count())
                .from(user)
                .where(user.userRole.eq(Role.ADMIN))
                .fetchOne();
    }
    public Long totalUserCountQuery(QUser user) {
        return queryFactory
                .select(user.count())
                .from(user)
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
            log.info("value.getProper(): " + value.getOrder());
            log.info("param sort.getProper(): " + sort.getOrder());
            if (sort == value) {
                Path<Object> path = Expressions.path(Object.class, user, value.getProperty());
                return new OrderSpecifier(value.getOrder(), path);
            }
        }
        return null;
    }


    public QBean<AdminUserResponseDto> userSelect(QUser user) {
        return Projections.fields(
                AdminUserResponseDto.class,
                user.userEmail, user.userPw, user.createdAt, user.userNick,
                        user.userRole
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
    public Page<AdminUserResponseDto> getAllMember(Pageable pageable, AdminUserPaginationRequestDto dto) {

        QUser user = QUser.user;
        QStore store = QStore.store;

        JPQLQuery<AdminUserResponseDto> results = queryFactory
                .select(userSelect(user))
                .from(user)
                .orderBy(getOrderSpecifiers(dto.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<AdminUserResponseDto> userList = results.fetch();

        return new PageImpl<>(userList, pageable, totalUserCountQuery(user));
    }
}
