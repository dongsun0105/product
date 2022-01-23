package com.musinsa.product.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import com.musinsa.product.exception.ExceptionCode;
import com.musinsa.product.exception.GoodsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiter rateLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!rateLimiter.tryAcquire()) {
            throw new GoodsException(ExceptionCode.PRD002);
        }

        return true;
    }

}
