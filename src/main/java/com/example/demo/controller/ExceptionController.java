package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ResourceNotFoundException2;
import com.example.demo.exception.ResourceNotFoundException3;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found", new ResourceNotFoundException());
    }

    @GetMapping(value = "/notfound3")
    public void notFound3(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","wang");
        throw new ResourceNotFoundException3(map);
    }

    @GetMapping(value = "/illegalArgument")
    public void illegalArugument(){
        throw new IllegalArgumentException();
    }


}
