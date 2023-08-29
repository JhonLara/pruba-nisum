package com.prueba.nisum.service;


import com.prueba.nisum.dto.ResponseDTO;
import com.prueba.nisum.entity.Phone;
import com.prueba.nisum.entity.UserLogin;

import java.util.List;

/**
 * Clase encargada de declarar la estructura de las capacidades que se expondran
 * en la implementación del servicio
 *
 * @author Jhon Lara
 */
public interface NisumService {

    ResponseDTO createLoginUser(UserLogin userLogin);

    void deleteUserLogin(Long idUserLogin);

    void deletePhone(Long idPhone);

    List<Phone> getPhoneList();

    List<UserLogin> getUserLoginList();




}
