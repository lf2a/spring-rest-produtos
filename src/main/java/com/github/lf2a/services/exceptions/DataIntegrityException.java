package com.github.lf2a.services.exceptions;

/**
 * <h1>ObjectNotFoundException.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
