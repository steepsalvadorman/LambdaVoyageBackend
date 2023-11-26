package com.grupo03.Lambda_Voyage.api.controllers.error_handler;

import com.grupo03.Lambda_Voyage.api.models.responses.BaseErrorResponse;
import com.grupo03.Lambda_Voyage.api.models.responses.ErrorResponse;
import com.grupo03.Lambda_Voyage.util.exceptions.ForbiddenCustomerException;
import com.grupo03.Lambda_Voyage.util.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenCustomerHandler {

    @ExceptionHandler(ForbiddenCustomerException.class)
    public BaseErrorResponse handleIdNotFound(ForbiddenCustomerException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .code(HttpStatus.FORBIDDEN.value())
                .build();
    }
}
