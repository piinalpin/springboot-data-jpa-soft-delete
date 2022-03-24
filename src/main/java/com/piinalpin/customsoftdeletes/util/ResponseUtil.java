package com.piinalpin.customsoftdeletes.util;

import java.time.LocalDateTime;

import com.piinalpin.customsoftdeletes.http.dto.base.BaseResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {}

    public static ResponseEntity<Object> build(String responseCode, String message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(responseCode, message, data), httpStatus);
    }

    private static BaseResponse build(String responseCode, String message, Object data) {
        return BaseResponse.builder()
                .timestamp(LocalDateTime.now())
                .responseCode(responseCode)
                .message(message)
                .data(data)
                .build();
    }
    
}
