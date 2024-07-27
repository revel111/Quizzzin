package com.example.quizzzin.configurations;

import com.example.quizzzin.enums.RoleType;
import com.example.quizzzin.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.quizzzin.enums.RoleType.*;

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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAuthority(ADMIN.name())
                        .requestMatchers("/puzzles/solve").authenticated()
                        .requestMatchers("/settings/account").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/home"/*, true*/)
                        .failureUrl("/login")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return email -> userService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' not found"));
    }
}