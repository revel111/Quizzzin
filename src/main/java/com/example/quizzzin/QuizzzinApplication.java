package com.example.quizzzin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.quizzzin.*")
@ComponentScan(basePackages = {"com.example.quizzzin.*"})
@EntityScan("com.example.quizzzin.*")
public class QuizzzinApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuizzzinApplication.class, args);
    }
}