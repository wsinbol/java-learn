package com.example.demo.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Data
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException2 extends RuntimeException {
    public ResourceNotFoundException2(){

    }

    public ResourceNotFoundException2(String message){
        super(message);
    }
}
