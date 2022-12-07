package com.lochlann.assignment2.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void messageNotReadableException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing JSON");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintInDBHandler(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.CONFLICT.value(), "Causing a conflict in the database");
    }

}
