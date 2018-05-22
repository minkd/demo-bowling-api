package com.mink.demo.bowlingapi.api.rest.exceptions;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ErrorResponse {

    private Integer status;

    private String error;

    private String message;

    private String timeStamp;

    private String path;

    private Set<ErrorDetail> errorDetails = new HashSet<>();

    public ErrorResponse(int status, String timeStamp, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
