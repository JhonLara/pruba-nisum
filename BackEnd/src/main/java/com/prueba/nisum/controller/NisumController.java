package com.prueba.nisum.controller;

import com.prueba.nisum.dto.ResponseDTO;
import com.prueba.nisum.entity.NisumException;
import com.prueba.nisum.entity.Phone;
import com.prueba.nisum.entity.UserLogin;
import com.prueba.nisum.service.NisumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Clase encargada de mapear los endpoints REST de la app
 *
 * @author Jhon Lara
 */
@RestController
@RequestMapping(value = "/nisum")
public class NisumController {

    @Autowired
    private NisumService nisumService;

    @PostMapping("/login-user")
    public ResponseEntity<ResponseDTO> createLoginUser(@RequestBody UserLogin userLogin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(nisumService.createLoginUser(userLogin));
        } catch (NisumException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.builder().message(exc.getMessage()).build());
        }
    }

    @DeleteMapping("/{ID_USER}")
    public ResponseEntity<String> deleteLoginUser(@PathVariable(name = "ID_USER") Long idUserLogin) {
        try {
            nisumService.deleteUserLogin(idUserLogin);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NisumException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @DeleteMapping("/phone/{ID_PHONE}")
    public ResponseEntity<String> deletePhone(@PathVariable(name = "ID_PHONE") Long idPhone) {
        try {
            nisumService.deletePhone(idPhone);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NisumException exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @GetMapping()
    public List<UserLogin> getLoginUserList() {
        return nisumService.getUserLoginList();
    }
    @GetMapping("/phone")
    public List<Phone> getPhoneList() {
        return nisumService.getPhoneList();
    }

}
