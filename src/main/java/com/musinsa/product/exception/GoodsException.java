package com.musinsa.product.exception;

import lombok.Getter;

@Getter
public class GoodsException extends RuntimeException {
    private String code;
    private String message;

    public GoodsException(ExceptionCode exceptionCode) {
        super();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
