package com.musinsa.product.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class CommonResponseBody<T> {
    public static final String RESPONSE_CODE_OK = "0";

    private String code;
    private String message;
    private T data;

    public CommonResponseBody() {
        this.code = RESPONSE_CODE_OK;
        this.message = "";
        this.data = null;
    }

    public CommonResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public CommonResponseBody(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResponseBody(T data) {
        this.code = RESPONSE_CODE_OK;
        this.message = "";
        this.data = data;
    }
}