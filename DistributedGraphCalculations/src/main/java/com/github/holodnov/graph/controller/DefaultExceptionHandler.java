package com.github.holodnov.graph.controller;

import com.github.holodnov.graph.exception.UnparseableGraphDataException;
import com.github.holodnov.graph.http.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Kyrylo Holodnov
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(UnparseableGraphDataException.class)
    @ResponseBody
    public Response handleUnparsableGraphDataExceptionHandler(HttpServletRequest req, UnparseableGraphDataException e) {
        return new Response().setError(e.getMessage());
    }
}
