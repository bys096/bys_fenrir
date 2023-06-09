package com.fenrir.guruguru_spring.domain.review.repository;

import com.fenrir.guruguru_spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByUser_UserId(Long userId);

    @Query("select r from Review r where r.user.userId = :userId and r.store.storeCode = :storeCode")
    Optional<Review> getReviewByUserId(@PathParam("userId") Long userId, @PathParam("storeCode") String storeCode);



    @Query("SELECT r FROM Review r JOIN Store s ON s.storeCode = r.store.storeCode JOIN OwnerRegister own ON own.store.storeCode = r.store.storeCode where r.rid = :reviewId AND (r.user.userId = :userId OR own.owner.userId = :userId)")
    Optional<Review> findReviewsByUserIdAndStoreCodeOrOwner(@PathParam("reviewId") Long reviewId, @PathParam("userId") Long userId);


    Optional<Review> findByUser_UserIdAndStore_StoreCode(@PathParam("userId") Long userId, @PathParam("storeCode") String storeCode);

}
