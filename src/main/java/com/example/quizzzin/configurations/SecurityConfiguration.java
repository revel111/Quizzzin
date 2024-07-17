package com.example.quizzzin.configurations;

import com.example.quizzzin.models.entities.User;
import com.example.quizzzin.repositories.UserRepository;
import com.example.quizzzin.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(form -> form.loginPage("/quizzzin/login").defaultSuccessUrl("/home").permitAll())
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home").permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> {
            Optional<User> user = userService.findUserByEmail(username);
            if (user.isPresent())
                return user.get();

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
}