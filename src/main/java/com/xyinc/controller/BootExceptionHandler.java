package com.xyinc.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class BootExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<String> handleBadInput(ConstraintViolationException ex) {
        String response = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(" - "));

        return new ResponseEntity<>("{\"message\": " + response + "}", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleServerException(Exception ex) {
        return new ResponseEntity<>("{\"message\": " + ex.toString() + "}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}