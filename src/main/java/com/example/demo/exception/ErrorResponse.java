package com.example.demo.exception;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ErrorResponse {
    private String message;
    private String errorTypeName;

    public ErrorResponse(){

    }

    public ErrorResponse(Exception e){
        this(e.getClass().getName(), e.getMessage());
    }

    public ErrorResponse(String errorTypeName, String message) {
        this.message = message;
        this.errorTypeName = errorTypeName;
    }

}
