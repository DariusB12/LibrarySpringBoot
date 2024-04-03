package org.example.libraryproject.validator;

import org.example.libraryproject.exception.ValidationException;

public interface IValidator<E> {
    void validate(E entity) throws ValidationException;
}
