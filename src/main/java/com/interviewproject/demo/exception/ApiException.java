package com.interviewproject.demo.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class ApiException {
    private final String message;
    @JsonIgnore
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
    @JsonInclude(Include.NON_NULL)
    private final Map<String, String> fieldError;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus) {
        this(message,throwable,httpStatus,null);
    }

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, Map<String, String> fieldError) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timeStamp = ZonedDateTime.now();
        this.fieldError = fieldError;
    }

    public ApiException(MethodArgumentNotValidException exception) {
        this.message = "Campi non correttamente valorizzati!";
        this.throwable = exception;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.timeStamp = ZonedDateTime.now();
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        this.fieldError = errors;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public Map<String, String> getFieldError() {
        return fieldError;
    }
}
