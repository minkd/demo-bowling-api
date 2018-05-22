package com.mink.demo.bowlingapi.api.rest.exceptions;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

/**
 * RestControllerAdvice
 * <p>
 * This class defines error handling scenarios for exceptions that occur in processing. Any exception can be handled
 * through here, minimizing the need to define in any given route or elsewhere throughout the call stack.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /*******************************
     *      Inbound exceptions
     *******************************/

    //  javax.validation handler
    //  validation against request objects
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RequestValidationException.class})
    public ErrorResponse handleRequestValidationException(HttpServletRequest request, RequestValidationException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.InvalidRequest.name(),
                "This request contains improperly formatted data",
                request.getRequestURI());
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorResponse.getErrorDetails().add(new ErrorDetailBody(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
        }

        return errorResponse;
    }

    //  javax.validation handler
    //  validation against request objects
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(), ErrorType.InvalidRequest.name(),
                "This request contains improperly formatted data",
                request.getRequestURI());
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errorResponse.getErrorDetails().add(new ErrorDetailBody(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()));
        }
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberFormatException.class})
    public ErrorResponse handleNumberFormatException(HttpServletRequest request, NumberFormatException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceDefinition.name(),
                String.format("This request contains a mismatched type: %s", exception.getMessage()),
                request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ErrorResponse handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceDefinition.name(),
                "This request contains a mismatched type",
                request.getRequestURI());
        errorResponse.getErrorDetails().add(new ErrorDetailParameter(exception.getName(), exception.getValue(), String.format("This parameter must be of data type '%s'", exception.getRequiredType().getName())));
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MismatchedInputException.class})
    public ErrorResponse handleMismatchedInputException(HttpServletRequest request, MismatchedInputException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceDefinition.name(),
                "This request contains a mismatched type",
                request.getRequestURI());
        errorResponse.getErrorDetails().add(new ErrorDetailParameter(exception.getTargetType().getName(), null, String.format("This parameter must be of data type '%s'", exception.getTargetType().getName())));
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ErrorResponse handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.InvalidRequest.name(),
                "The request body is required and is missing or cannot be read",
                request.getRequestURI());
    }

    /*******************************
     *      Outbound exceptions
     *******************************/

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({EntityExistsException.class})
    public ErrorResponse handleEntityExistsException(HttpServletRequest request, EntityExistsException exception) {
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceConflict.name(),
                String.format("Resource already exists at %s", request.getRequestURI()),
                request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
    public ErrorResponse handleEntityNotFoundException(HttpServletRequest request, Exception exception) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceNotFound.name(),
                String.format("Unable to find a resource at %s", request.getRequestURI()),
                request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ErrorResponse handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceConflict.name(),
                String.format("This request violates a constraint on this resource: %s", exception.getConstraintName()),
                request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ErrorResponse handleDataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException exception) {
        String errorMessage = exception.getRootCause().getMessage();
        errorMessage = errorMessage.substring(errorMessage.indexOf(System.lineSeparator()) + 1, errorMessage.length());
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceConflict.name(),
                String.format("This request violates a constraint on this resource: %s", errorMessage),
                request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ErrorResponse handleGeneralException(HttpServletRequest request, Exception exception) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now().toString(),
                ErrorType.ResourceGeneralError.name(),
                String.format("This request has a problem: %s", exception.getMessage()),
                request.getRequestURI());
    }

}
