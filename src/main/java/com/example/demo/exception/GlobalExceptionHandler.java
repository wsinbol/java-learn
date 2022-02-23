package com.example.demo.exception;

import com.example.demo.controller.ExceptionController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice(assignableTypes = {ExceptionController.class})
@ResponseBody
public class GlobalExceptionHandler {

     ErrorResponse resourceNotFound = new ErrorResponse(new ResourceNotFoundException("resource not found"));
     ErrorResponse illegalArgument =  new ErrorResponse(new IllegalArgumentException("参数错误"));

     @ExceptionHandler(value = Exception.class)
     public Object exceptionHandler(Exception e){
          if(e instanceof ResourceNotFoundException){
               return ResponseEntity.status(404).body(resourceNotFound);
          }

          if(e instanceof IllegalArgumentException){
               return ResponseEntity.status(404).body(illegalArgument);
          }

          if(e instanceof ResponseStatusException){
               return ResponseEntity.status(404).body("xxxx");
          }
          return null;
     }

}
