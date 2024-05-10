package org.example.libraryproject.validator;

import org.example.libraryproject.exception.exceptions.ValidationException;

public interface IValidator<E> {
    void validate(E entity) throws ValidationException;
}
