package com.musinsa.product.dto.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class CommonResponse<T> extends ResponseEntity<CommonResponseBody<T>> {

    public CommonResponse() {
        super(new CommonResponseBody<T>(), HttpStatus.OK);
    }

    public CommonResponse(T body) {
        super(new CommonResponseBody<T>(body), HttpStatus.OK);
    }

    public CommonResponse(String code, String message, @Nullable T body) {
        super(new CommonResponseBody<>(code, message, body), HttpStatus.OK);
    }

    public CommonResponse(String code, String message, @Nullable T body, HttpStatus status) {
        super(new CommonResponseBody<>(code, message, body), status);
    }

}