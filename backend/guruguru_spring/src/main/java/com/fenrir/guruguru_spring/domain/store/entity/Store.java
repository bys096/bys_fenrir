package com.fenrir.guruguru_spring.domain.store.entity;

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
@Table(name = "stores")
@DynamicInsert
public class Store {

    @Id
    @Column(name = "store_code")
    private String storeCode;

    @Column(name = "store_name")
    private String storeName;
}
