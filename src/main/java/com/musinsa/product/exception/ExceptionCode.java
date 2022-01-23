package com.musinsa.product.exception;

public enum ExceptionCode {

    PRD001("Product ID is invalid."),
    PRD002("Request block by rate limiter.");

    private String code;
    private String message;

    ExceptionCode(String message) {
        this.code = this.name();
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }
}
