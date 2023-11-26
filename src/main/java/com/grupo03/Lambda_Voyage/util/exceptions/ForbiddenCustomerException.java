package com.grupo03.Lambda_Voyage.util.exceptions;

public class ForbiddenCustomerException extends RuntimeException{

    public ForbiddenCustomerException(){
        super("This customer is blocked");
    }
}
