package org.example.libraryproject.exception;

import com.sun.jdi.InternalException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.InternalServerException;
import org.example.libraryproject.exception.exceptions.UserServiceException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Order(1)
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorObject> handleSecurityValidationException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(400)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorObject> handleSecurityExceptions(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUsernameNotFoundException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorObject> handleSignatureException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(400)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorObject> handleExpiredJwtException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorObject> handleDataIntegrityViolationException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(409)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorObject> handleRegularServiceException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(400)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorObject> handleInternalServerException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(500)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<ErrorObject> handleAuthServiceException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))))
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
}
