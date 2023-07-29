package com.blood.donation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long SerialVersionUID =1L;
    public ResourceNotFoundException(String Message){
        super(Message);
    }

}
