package com.mink.demo.bowlingapi.api.rest.exceptions;

import com.google.common.base.CaseFormat;
import lombok.Data;

@Data
public class ErrorDetailBody implements ErrorDetail {

    private String field;
    private Object value;
    private String message;

    public ErrorDetailBody(String field, Object value, String message) {
        this.field = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
        this.value = value;
        this.message = message;
    }
}
