package com.example.spring20232.config;

import com.example.spring20232.web.interceptors.IncomingRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationInterceptorConfiguration implements WebMvcConfigurer {


    private final IncomingRequestInterceptor incomingRequestInterceptor;


    public ApplicationInterceptorConfiguration(IncomingRequestInterceptor incomingRequestInterceptor) {
        this.incomingRequestInterceptor = incomingRequestInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingRequestInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
