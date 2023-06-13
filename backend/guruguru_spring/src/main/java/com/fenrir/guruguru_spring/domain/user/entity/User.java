package com.fenrir.guruguru_spring.domain.user.entity;

import com.fenrir.guruguru_spring.domain.user.dto.UserUpdateRequestDto;
import com.fenrir.guruguru_spring.global.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "users")
@DynamicInsert
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE users SET deleted = true where user_id=?")
public class User extends BaseEntity {

    @Column(nullable = false)
    private boolean deleted;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_email" ,unique = true)
    private String userEmail;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nick")
    private String userNick;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role userRole;


    public void updateUser(UserUpdateRequestDto dto) {
        this.userName = dto.getUserName();
        this.userNick = dto.getNickName();
        this.userPw = dto.getPw();
    }


}
