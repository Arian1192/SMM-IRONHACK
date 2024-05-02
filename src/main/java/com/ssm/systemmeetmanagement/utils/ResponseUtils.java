package com.ssm.systemmeetmanagement.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils {

    public static ResponseEntity<?> createResponse(HttpStatus status, String value){
            Map<String, String> response = new HashMap<>();
            response.put("response", value);
            return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(response);
    }
    public static ResponseEntity<?> notFoundResponse(String message){
        return createResponse(HttpStatus.NOT_FOUND, message);
    }
    public static ResponseEntity<?> noContentResponse(String message){
        return createResponse(HttpStatus.NO_CONTENT, message);
    }
}
