package com.example.demo.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "未找到该资源"),
    REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "请求数据格式验证失败");

    private int code;
    private HttpStatus status;
    private String message;


    ErrorCode(int code, HttpStatus httpStatus, String message){
        this.code = code;
        this.status = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
