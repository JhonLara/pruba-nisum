package com.prueba.nisum.entity;

/**
 * Exception creada para manejar los errores en la app
 *
 * @author Jhon Lara
 */
public class NisumException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NisumException(String message) {
        super(message);
    }
}
