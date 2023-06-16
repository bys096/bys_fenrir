package com.fenrir.guruguru_spring.domain.admin.repository;

import com.fenrir.guruguru_spring.domain.admin.dto.AdminOwnerResponseDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserPaginationRequestDto;
import com.fenrir.guruguru_spring.domain.admin.dto.AdminUserResponseDto;
import com.fenrir.guruguru_spring.domain.owner_register.entity.QOwnerRegister;
import com.fenrir.guruguru_spring.domain.owner_register.entity.QOwnerRegisterFile;
import com.fenrir.guruguru_spring.domain.reply.dto.ReplyResponseDto;
import com.fenrir.guruguru_spring.domain.reply.entity.QReply;
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

import java.util.Arrays;
import java.util.List;

import static com.fenrir.guruguru_spring.domain.owner_register.entity.QOwnerRegister.ownerRegister;
import static com.fenrir.guruguru_spring.domain.review.entity.QReview.review;
import static com.fenrir.guruguru_spring.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AdminRepositoryImpl implements AdminRepositoryCustom {

    private final JPAQueryFactory queryFactory;

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
            if (sort == value) {
                Path<Object> path = Expressions.path(Object.class, ownerRegister, value.getProperty());
                return new OrderSpecifier(value.getOrder(), path);
            }
        }
        return null;
    }
    private OrderSpecifier getUserOrderSpecifiers(Sort sort) {
        for (Sort value : Sort.values()) {
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
                user.userRole, user.userName, user.userId
        );
    }

    public QBean<AdminOwnerResponseDto> ownerSelect(QOwnerRegister owner, QUser user, QStore store, QOwnerRegisterFile file) {
        return Projections.fields(
                AdminOwnerResponseDto.class,
                user.userEmail, user.userPw, user.userNick, user.userName,
                store.storeName, store.storeCode, owner.createdAt, owner.orState,
                file.orFileName, owner.orId
        );
    }

    @Override
    public Page<AdminUserResponseDto> getAllMember(Pageable pageable, AdminUserPaginationRequestDto dto) {

        QUser user = QUser.user;
        JPQLQuery<AdminUserResponseDto> results = queryFactory
                .select(userSelect(user))
                .from(user)
                .orderBy(getUserOrderSpecifiers(dto.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<AdminUserResponseDto> userList = results.fetch();
        return new PageImpl<>(userList, pageable, totalUserCountQuery(user));
    }


    @Override
    public Page<AdminOwnerResponseDto> getAllOwner(Pageable pageable, AdminUserPaginationRequestDto dto) {

        QUser user = QUser.user;
        QOwnerRegister ownerRegister = QOwnerRegister.ownerRegister;
        QStore store = QStore.store;
        QOwnerRegisterFile file = QOwnerRegisterFile.ownerRegisterFile;

        JPQLQuery<AdminOwnerResponseDto> results = queryFactory
                .select(ownerSelect(ownerRegister, user, store, file))
                .from(ownerRegister)
                .leftJoin(user).on(ownerRegister.owner.eq(user))
                .leftJoin(store).on(ownerRegister.store.eq(store))
                .leftJoin(file).on(file.owner.eq(ownerRegister))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<AdminOwnerResponseDto> ownerList = results.fetch();
        Long count = results.fetchCount();

        return new PageImpl<>(ownerList, pageable, count);

    }
}
