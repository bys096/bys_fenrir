package com.fenrir.guruguru_spring.domain.store.entity;

import com.fenrir.guruguru_spring.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "favor_stores")
@DynamicInsert
public class FavorStore {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fs_id")
    private Long fsId;

    @Column(name = "store_code", unique = true)
    private String store_code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
