package com.example.demo.exception;

import java.util.Map;

public class ResourceNotFoundException3 extends BaseException{

    public ResourceNotFoundException3(ErrorCode errorCode, Map<String, Object> data, Throwable cause) {
        super(errorCode, data, cause);
    }

    public ResourceNotFoundException3(Map<String,Object> data){
        super(ErrorCode.RESOURCE_NOT_FOUND,data);
    }


}
