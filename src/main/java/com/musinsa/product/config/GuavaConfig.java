package com.musinsa.product.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuavaConfig {

    @Value("${app.rate-limit}")
    private int limit;

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(limit);
    }

}
