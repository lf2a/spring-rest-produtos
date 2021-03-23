package com.github.lf2a.services.exceptions;

/**
 * <h1>FileException.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/03/2021
 */
public class FileException extends RuntimeException {

    public FileException(String msg) {
        super(msg);
    }

    public FileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
