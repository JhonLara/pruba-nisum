package com.prueba.nisum.repository;

import com.prueba.nisum.entity.Phone;
import com.prueba.nisum.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findByUserLogin(UserLogin userLogin);
}
