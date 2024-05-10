package org.example.libraryproject.exception.exceptions;

public class AuthServiceException extends Exception{
    public AuthServiceException() {
        super();
    }

    public AuthServiceException(String message) {
        super(message);
    }

    public AuthServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
