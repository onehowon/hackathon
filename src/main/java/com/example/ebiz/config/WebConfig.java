package com.example.ebiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/public/");  // 다른 경로를 설정하여 정적 리소스 처리
        registry.addResourceHandler("/video/**").setCachePeriod(0);  // /video 경로를 정적 리소스 처리에서 제외
    }
}