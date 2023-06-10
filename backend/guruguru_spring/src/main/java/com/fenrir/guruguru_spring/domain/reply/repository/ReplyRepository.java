package com.fenrir.guruguru_spring.domain.reply.repository;

import com.fenrir.guruguru_spring.domain.reply.entity.Reply;
import com.fenrir.guruguru_spring.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

//    Optional<Reply> findByUser_UserIdoOrStore_Owner_UserId(Long userId);


}
