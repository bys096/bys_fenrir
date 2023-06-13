package com.fenrir.guruguru_spring.domain.review.entity;

import com.fenrir.guruguru_spring.domain.owner_register.entity.OwnerRegister;
import com.fenrir.guruguru_spring.domain.reply.entity.Reply;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.global.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

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
    private Integer reviewRating;

    @Column(name = "review_text")
    private String reviewText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_code")
    private Store store;

    @OneToMany(mappedBy = "review")
    private List<Reply> replyList;

    // image

    public void updateReview(String reviewText) {
        this.reviewText = reviewText;
    }
}
