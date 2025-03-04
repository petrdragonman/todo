package com.petr.todo.common.exceptions;

import java.util.ArrayList;
import java.util.Map;

import com.petr.todo.common.ValidationErrors;

public class ServiceValidationException extends Exception{
    private ValidationErrors errors;

    public ServiceValidationException(ValidationErrors errors) {
        this.errors = errors;
    }

    public ValidationErrors getErrors() {
        return errors;
    }
}
