package org.example.backendip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // No trailing slashes; include "null" to allow desktop/file:// origins if needed
        String[] allowedOrigins = {
            "http://localhost:5173",
            "https://d18275glmimxyg.cloudfront.net",
            "null"
        };

        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}