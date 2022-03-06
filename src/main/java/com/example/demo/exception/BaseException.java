package com.example.demo.exception;

import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Data
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HashMap<String, Object> data = new HashMap<>();

    public BaseException(ErrorCode errorCode, Map<String, Object> data, Throwable cause){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        if(!ObjectUtils.isEmpty(data)){
            this.data.putAll(data);
        }

    }

    public BaseException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        if(!ObjectUtils.isEmpty(data)){
            this.data.putAll(data);
        }
    }


}
