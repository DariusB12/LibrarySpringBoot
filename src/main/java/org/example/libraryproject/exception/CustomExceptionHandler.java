package org.example.libraryproject.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
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

import java.time.LocalDateTime;
@Order(1)
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorObject> handleSecurityValidationException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(400)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorObject> handleSecurityExceptions(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUsernameNotFoundException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorObject> handleSignatureException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(400)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorObject> handleExpiredJwtException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(401)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorObject> handleDataIntegrityViolationException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(409)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ErrorObject> handleRegularServiceException(Exception ex) {
        ErrorObject errorObject;

        errorObject = ErrorObject.builder()
                .statusCode(400)
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
