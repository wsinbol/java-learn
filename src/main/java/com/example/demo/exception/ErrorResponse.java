package com.example.demo.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class ErrorResponse {

    private int code;
    private HttpStatus status;
    private String message;
    private String path;
    private Instant timestamp;
    private HashMap<String, Object> data = new HashMap<String,Object>();

    public ErrorResponse(){

    }

    public ErrorResponse(int code, HttpStatus status, String message, String path, Map<String,Object> data){
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public ErrorResponse(BaseException ex, String path){
        this(ex.getErrorCode().getCode(),ex.getErrorCode().getStatus(),ex.getErrorCode().getMessage(),path,
                ex.getData());
    }

    // 第一版异常响应
    /*
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

     */

}
