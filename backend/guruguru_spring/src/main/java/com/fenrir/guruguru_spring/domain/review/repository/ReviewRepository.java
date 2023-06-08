package com.fenrir.guruguru_spring.domain.review.repository;

import com.fenrir.guruguru_spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUser_UserId(Long userId);

    @Query("select r from Review r where r.user.userId = :userId and r.store.storeCode = :storeCode")
    Optional<Review> getReviewByUserId(@PathParam("userId") Long userId, @PathParam("storeCode") String storeCode);

}
