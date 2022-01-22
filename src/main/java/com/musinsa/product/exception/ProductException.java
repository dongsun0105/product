package com.musinsa.product.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {
    private String code;
    private String message;

    public ProductException(ExceptionCode exceptionCode) {
        super();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
