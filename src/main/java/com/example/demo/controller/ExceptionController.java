package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotFoundException2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping(value = "/notFound")
    public void notFound(){
        throw new ResourceNotFoundException();
    }

    @GetMapping(value = "/notfound2")
    public void notFound2(){
//        throw new ResourceNotFoundException2("Sorry, the resourse not found!");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found", new ResourceNotFoundException2());
    }

    @GetMapping(value = "/illegalArgument")
    public void illegalArugument(){
        throw new IllegalArgumentException();
    }


}
