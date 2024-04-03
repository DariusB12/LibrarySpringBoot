package org.example.libraryproject.exception;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

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
}
