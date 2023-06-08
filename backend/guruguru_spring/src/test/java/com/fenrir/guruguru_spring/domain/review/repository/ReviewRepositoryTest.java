package com.fenrir.guruguru_spring.domain.review.repository;

import com.fenrir.guruguru_spring.domain.review.entity.Review;
import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.store.repository.StoreRepository;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void createReview() {
        Optional<Store> store = storeRepository.findByStoreCode("J001039795");
        Optional<User> user = userRepository.findById(2L);

        IntStream.rangeClosed(1, 30).forEach(i -> {
            Review review = Review.builder()
                    .reviewRating(5)
                    .reviewText("리뷰..." + i)
                    .user(user.get())
                    .store(store.get())
                    .build();
            reviewRepository.save(review);
        });
    }

}