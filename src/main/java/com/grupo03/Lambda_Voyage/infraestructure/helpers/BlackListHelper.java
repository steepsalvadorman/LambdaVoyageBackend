package com.grupo03.Lambda_Voyage.infraestructure.helpers;

import com.grupo03.Lambda_Voyage.util.exceptions.ForbiddenCustomerException;
import org.springframework.stereotype.Component;

@Component
public class BlackListHelper {

    public void isInBlackListCustomer(String customerId){
        if (customerId.equals("VIKI771012HMCRG093")){
            throw new ForbiddenCustomerException();
        }
    }
}
