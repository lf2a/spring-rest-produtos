package com.github.lf2a.services.exceptions;

/**
 * <h1>AuthorizationException.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 22/03/2021
 */
public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
