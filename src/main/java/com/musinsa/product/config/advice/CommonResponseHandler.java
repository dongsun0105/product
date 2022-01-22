package com.musinsa.product.config.advice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.product.dto.common.CommonResponseBody;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonResponseHandler implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (selectedContentType != MediaType.APPLICATION_JSON) {
            return body;
        }

        Map<String, Object> map = objectMapper.convertValue(body, new TypeReference<Map<String, Object>>() {});

        CommonResponseBody<Object> responseBody = new CommonResponseBody<>();
        if (map.containsKey("data")) {
            responseBody.setData(ObjectUtils.isNotEmpty(map.get("data")) ? map.get("data") : null);
            responseBody.setMessage(ObjectUtils.isNotEmpty(map.get("message")) ? map.get("message").toString() : "");
        } else {
            return body;
        }

        return responseBody;
    }
}
