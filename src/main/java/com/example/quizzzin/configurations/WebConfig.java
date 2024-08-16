package com.example.quizzzin.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * The {@code WebConfig} class is a Spring configuration class responsible for defining beans related to
 * web and RESTful services.
 * <p>
 * This configuration class is used to set up the {@link RestTemplate} bean, which is a synchronous client
 * for making HTTP requests and interacting with RESTful services. It provides a convenient way to consume
 * RESTful APIs and is used throughout the application for external service communication.
 * </p>
 */
@Configuration
@EnableScheduling
public class WebConfig/* implements WebMvcConfigurer*/ {
//    @Value("${site.base.url}")
//    private String siteBaseUrl;

    /**
     * Creates and configures a {@link RestTemplate} bean.
     * <p>
     * The {@link RestTemplate} is used for making HTTP requests to external services or APIs. It provides
     * methods for various HTTP operations such as GET, POST, PUT, DELETE, etc.
     * </p>
     *
     * @return a {@link RestTemplate} instance configured for use in the application
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//                .allowedOrigins("http://localhost:8080/quizzin")
//                .allowedMethods("GET", "PUT", "POST", "DELETE");
//    }
}