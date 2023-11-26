package com.grupo03.Lambda_Voyage.api.controllers.error_handler;

import com.grupo03.Lambda_Voyage.api.models.responses.BaseErrorResponse;
import com.grupo03.Lambda_Voyage.api.models.responses.ErrorResponse;
import com.grupo03.Lambda_Voyage.api.models.responses.ErrorsResponse;
import com.grupo03.Lambda_Voyage.util.exceptions.IdNotFoundException;
import com.grupo03.Lambda_Voyage.util.exceptions.UserNameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {
    @ExceptionHandler({IdNotFoundException.class, UserNameNotFoundException.class})
    public BaseErrorResponse handleIdNotFound(RuntimeException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleIdNotFound(MethodArgumentNotValidException exception){
        var errors = new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error-> errors.add(error.getDefaultMessage()));

        return ErrorsResponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();


    }
}
