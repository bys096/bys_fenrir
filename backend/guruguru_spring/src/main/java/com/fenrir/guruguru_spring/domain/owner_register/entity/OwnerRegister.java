package com.fenrir.guruguru_spring.domain.owner_register.entity;

import com.fenrir.guruguru_spring.domain.store.entity.Store;
import com.fenrir.guruguru_spring.domain.user.entity.User;
import com.fenrir.guruguru_spring.global.common.entity.BaseEntity;
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
@Table(name = "owner_registers")
@DynamicInsert
public class OwnerRegister extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "or_id")
    private Long orId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "or_state")
    private Integer orState;
    // image thumbnail?

    @OneToOne
    @JoinColumn(name = "store_code")
    private Store store;


    public void acceptOwner() {
        this.orState = 1;
    }
}
