package com.internship.oasis.oasistaskone.exceptions;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists(String message){
        super(message);
    }
}
