package com.example.demo.exception;

import lombok.Data;



@Data
public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
