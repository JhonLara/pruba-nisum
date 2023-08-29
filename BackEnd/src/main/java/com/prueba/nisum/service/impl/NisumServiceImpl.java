package com.prueba.nisum.service.impl;

import com.prueba.nisum.config.ProjectDetails;
import com.prueba.nisum.dto.ResponseDTO;
import com.prueba.nisum.entity.NisumException;
import com.prueba.nisum.entity.Phone;
import com.prueba.nisum.entity.UserLogin;
import com.prueba.nisum.repository.PhoneRepository;
import com.prueba.nisum.repository.UserLoginRepository;
import com.prueba.nisum.service.JwtService;
import com.prueba.nisum.service.NisumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Clase encargada de mapear y dar comportamiento a las capacidades de los
 * servicios
 *
 * @author Jhon Lara
 */
@Service
public class NisumServiceImpl implements NisumService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ProjectDetails projectDetails;


    @Autowired
    private JwtService jwtService;

    private static final String REGEX_VALIDATE_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

    /**
     * Constantes con las cadenas de mensaje para mostrar al usuario
     */
    public static final String INVALID_EMAIL = "El correo ya esta registrado";

    public static final String INVALID_FORMAT_EMAIL = "Formato de email invalido";

    public static final String INVALID_FORMAT_PASSWORD = "Formato de password invalido";

    public static final String USER_CORRECT_CREATED = "Usuario creado con exito";

    @Override
    public ResponseDTO createLoginUser(UserLogin userLogin) {

        List<UserLogin> userLoginByEmailList = userLoginRepository.findByEmail(userLogin.getEmail());

        if (!userLoginByEmailList.isEmpty()) {
            throw new NisumException(INVALID_EMAIL);
        }
        if (!validateRegex(userLogin.getEmail(), REGEX_VALIDATE_EMAIL)) {
            throw new NisumException(INVALID_FORMAT_EMAIL);
        }

        if (!validateRegex(userLogin.getPassword(), projectDetails.getProjectRegex())) {
            throw new NisumException(INVALID_FORMAT_PASSWORD);
        }

        long uuid = UUID.randomUUID().getMostSignificantBits();

        if (uuid < 0L) {
            uuid = uuid * -1L;
        }
        Long finalUuid = uuid;

        userLogin.setIdUserLogin(finalUuid);
        String token = jwtService.generateToken(userLogin.getEmail());
        userLogin.setToken(token);
        userLogin.setIdUserLogin(finalUuid);
        userLogin.setCreated(LocalDate.now());
        userLogin.setLastLogin(LocalDate.now());
        userLogin.setIsActive(Boolean.TRUE);
        userLoginRepository.save(userLogin);

        userLogin.getPhones().forEach(phone ->
        {
            phone.setUserLogin(UserLogin.builder().idUserLogin(finalUuid).build());
            phoneRepository.save(phone);
        });

        return ResponseDTO.builder().idUserLogin(uuid).token(token).last_login(LocalDate.now()).created(LocalDate.now()).isActive(Boolean.TRUE).message(USER_CORRECT_CREATED).build();
    }

    private Boolean validateRegex(String email, String regex) {
        Pattern pattern = Pattern
                .compile(regex);
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public void deleteUserLogin(Long idUserLogin) {
        List<Phone> phoneList = phoneRepository.findByUserLogin(UserLogin.builder().idUserLogin(idUserLogin).build());
        phoneList.forEach(phone -> phoneRepository.delete(phone));
        userLoginRepository.deleteById(idUserLogin);
    }

    public void deletePhone(Long idPhone) {
        phoneRepository.deleteById(idPhone);
    }

    @Override
    public List<UserLogin> getUserLoginList() {
        List<UserLogin> userList = userLoginRepository.findAll();
        userList.forEach(userLogin -> {
            List<Phone> phoneList = phoneRepository.findByUserLogin(userLogin);
            userLogin.setPhones(phoneList);
        });
        return userList;
    }

    @Override
    public List<Phone> getPhoneList() {
        return phoneRepository.findAll();
    }

}
