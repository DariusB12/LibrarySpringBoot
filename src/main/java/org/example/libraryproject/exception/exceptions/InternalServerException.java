package org.example.libraryproject.exception.exceptions;

import jdk.jshell.spi.ExecutionControl;

public class InternalServerException extends Exception {
    public InternalServerException() {
        super();
    }

    public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
