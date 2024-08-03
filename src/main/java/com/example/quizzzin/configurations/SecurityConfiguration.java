package com.example.quizzzin.configurations;

import com.example.quizzzin.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

import static com.example.quizzzin.enums.RoleType.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    //    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    private final DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   UserDetailsService userDetailsService) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAuthority(ADMIN.name())
                        .requestMatchers("/puzzles/solve").authenticated() // TODO same as for rating
                        .requestMatchers("/puzzles/*/rate").authenticated() //TODO add filter for checking whether it was rated
                        .requestMatchers("/account").authenticated()
                        .anyRequest().permitAll()
                )
//                .rememberMe(x -> x.userDetailsService(userDetailsService))
                .rememberMe(x -> x.tokenRepository(persistentTokenRepository()))
//                .exceptionHandling(x -> x.authenticationEntryPoint(userAuthenticationEntryPoint))
//                .addFilterBefore(new EmailPasswordAuthFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new CookieAuthenticationFilter(), EmailPasswordAuthFilter.class)
//                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/home"/*, true*/)
                        .failureUrl("/login")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/home")
                                .permitAll()
//                        .deleteCookies(CookieAuthenticationFilter.COOKIE_NAME)
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return email -> userService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' not found"));
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }
}