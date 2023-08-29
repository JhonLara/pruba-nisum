package com.prueba.nisum.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @Id
    @Column(name = "ID_USER_LOGIN")
    private Long idUserLogin;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATED")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate created;

    @Column(name = "MODIFIED")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate modified;

    @Column(name = "LAST_LOGIN")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate lastLogin;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @OneToMany(mappedBy = "userLogin")
    @JsonBackReference(value = "userLogin")
    private List<Phone> phones;
}
