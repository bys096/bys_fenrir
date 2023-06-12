package com.fenrir.guruguru_spring.domain.owner_register.entity;

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
@Table(name = "owner_register_files")
@DynamicInsert
public class OwnerRegisterFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "or_file_id")
    private Long orFileId;

    @Column(name = "or_file_name")
    private String orFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "or_id")
    private OwnerRegister owner;
}
