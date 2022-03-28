package com.piinalpin.customsoftdeletes.util;

import java.time.LocalDateTime;

import com.piinalpin.customsoftdeletes.constant.AppConstant;
import com.piinalpin.customsoftdeletes.http.dto.base.BaseResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> ResponseEntity<Object> build(AppConstant.ResponseCode responseCode, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(responseCode, data), httpStatus);
    }

    private static <T> BaseResponse<T> build(AppConstant.ResponseCode responseCode, T data) {
        return BaseResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .responseCode(responseCode.name())
                .message(responseCode.getMessage())
                .data(data)
                .build();
    }
    
}
