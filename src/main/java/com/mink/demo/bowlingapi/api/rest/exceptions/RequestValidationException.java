package com.mink.demo.bowlingapi.api.rest.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.BindingResult;

@Data
@EqualsAndHashCode(callSuper = true)
public class RequestValidationException extends RuntimeException {

    private final Object request;

    private BindingResult bindingResult;

    public RequestValidationException(Object request, BindingResult bindingResult, String message) {
        super(message);
        this.request = request;
        this.bindingResult = bindingResult;
    }

}
