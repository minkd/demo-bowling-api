package com.mink.demo.bowlingapi.api.rest.exceptions;

import lombok.Data;

@Data
public class ErrorDetailParameter implements ErrorDetail {

    private String parameter;
    private Object value;
    private String message;

    public ErrorDetailParameter(String parameter, Object value, String message) {
        this.parameter = parameter;
        this.value = value;
        this.message = message;
    }
}
