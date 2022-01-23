package com.musinsa.product.config.advice;

import com.musinsa.product.dto.common.CommonResponseBody;
import com.musinsa.product.exception.GoodsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

	private static final String SYSTEM_ERROR_CODE = "SYS001";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponseBody<String>> handleException(Exception exception) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		String errorCode = exception instanceof GoodsException ?
				((GoodsException)exception).getCode() : SYSTEM_ERROR_CODE;

		CommonResponseBody commonResponseBody = new CommonResponseBody<String>(errorCode, exception.getMessage());
		return new ResponseEntity<>(commonResponseBody, httpHeaders, HttpStatus.OK);
	}

}
