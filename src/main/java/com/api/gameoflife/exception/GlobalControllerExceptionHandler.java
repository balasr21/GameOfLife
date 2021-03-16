package com.api.gameoflife.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.gameoflife.model.ErrorResponse;


@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({InvalidRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleBadRequest(HttpServletRequest req, Throwable exception) {
        return new ErrorResponse("Invalid request " + exception.getMessage());
    }

}
