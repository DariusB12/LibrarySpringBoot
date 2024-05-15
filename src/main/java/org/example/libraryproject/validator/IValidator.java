package org.example.libraryproject.validator;

import org.example.libraryproject.exception.exceptions.AuthServiceException;
import org.example.libraryproject.exception.exceptions.InternalServerException;
import org.example.libraryproject.exception.exceptions.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;

public interface IValidator<E> {
    void validate(E entity) throws ValidationException, DataIntegrityViolationException, InternalServerException, AuthServiceException;
}
