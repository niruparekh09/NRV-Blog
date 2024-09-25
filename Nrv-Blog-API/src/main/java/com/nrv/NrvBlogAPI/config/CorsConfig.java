package com.nrv.NrvBlogAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to configure Cross-Origin-Resource-Sharing.
 * Refer <a href="https://spring.io/guides/gs/rest-service-cors">this</a> to learn more.
 * <br/>
 * This class is implemented so that our client can access all methods from our server.
 *
 * @author Nirav Parekh
 * @see WebMvcConfigurer
 * @since 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allow CORS for all endpoints
                .allowedOrigins("http://localhost:5173") // Allow requests from localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify allowed methods
                .allowedHeaders("*") // Specify allowed headers
                .allowCredentials(true) // Allow credentials (e.g., cookies)
                .maxAge(3600); // Max age of preflight requests
    }
}
