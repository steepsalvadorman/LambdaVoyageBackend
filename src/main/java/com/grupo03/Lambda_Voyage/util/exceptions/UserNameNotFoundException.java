package com.grupo03.Lambda_Voyage.util.exceptions;

public class UserNameNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "User not exist in %s";

    public UserNameNotFoundException(String tableName){
            super(String.format(ERROR_MESSAGE, tableName));
        }

}

