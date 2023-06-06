package com.fenrir.guruguru_spring.domain.review.entity;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.global.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.lang.reflect.Member;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "reviews")
@DynamicInsert
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long rid;

    @Column(name = "review_rating")
    private int reviewRating;

    @Column(name = "review_text")
    private String reviewText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // image
}
