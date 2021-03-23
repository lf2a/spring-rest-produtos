package com.github.lf2a.resources.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.github.lf2a.services.exceptions.AuthorizationException;
import com.github.lf2a.services.exceptions.DataIntegrityException;
import com.github.lf2a.services.exceptions.FileException;
import com.github.lf2a.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>ResourceExceptionHandler.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> error.addError(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorizationException(AuthorizationException exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.FORBIDDEN.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(standardError);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> fileException(FileException exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException exception, HttpServletRequest request) {
        var httpStatus = HttpStatus.valueOf(exception.getErrorCode());
        StandardError standardError = new StandardError(httpStatus.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(httpStatus).body(standardError);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClientException(AmazonClientException exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception exception, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}
