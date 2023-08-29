package com.prueba.nisum.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @Column(name = "ID_PHONE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq_gen")
    @SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "MY_ENTITY_SEQ", allocationSize = 1)
    private Long idPhone;

    @Column(name = "NUMBER")
    private Long number;

    @Column(name = "CITY_CODE")
    private Long cityCode;

    @Column(name = "CONTRY_CODE")
    private Long contryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER_LOGIN")
    @JsonProperty(access = Access.WRITE_ONLY)
    @JsonBackReference(value = "ID_EXAM")
    private UserLogin userLogin;
}
