package com.grupo03.Lambda_Voyage.util.exceptions;

public class IdNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Record no exist in %s";

    public IdNotFoundException(String tableName){
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
