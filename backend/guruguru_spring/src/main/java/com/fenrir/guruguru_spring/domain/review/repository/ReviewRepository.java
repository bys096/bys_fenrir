package com.fenrir.guruguru_spring.domain.review.repository;

import com.fenrir.guruguru_spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUser_UserId(Long userId);
}
