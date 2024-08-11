package com.example.quizzzin.configurations;

import com.example.quizzzin.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static com.example.quizzzin.enums.AuthorityType.*;

/**
 * The {@code SecurityConfiguration} class is a configuration class responsible for setting up
 * security settings for the application.
 * <p>
 * This class configures HTTP security, user authentication, password encoding, and other security-related
 * features. It uses Spring Security's fluent API to define security constraints, such as authentication
 * and authorization rules, form login, logout handling, and token management for "remember me" functionality.
 * </p>
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    //    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

    private final DataSource dataSource;

    /**
     * Defines a {@link PasswordEncoder} bean used for encoding user passwords.
     * <p>
     * The {@link BCryptPasswordEncoder} is used for hashing passwords with the BCrypt algorithm, providing
     * secure password storage.
     * </p>
     *
     * @return a {@link PasswordEncoder} instance configured with BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain for HTTP requests.
     * <p>
     * This method sets up security constraints such as URL authorization, form login, and logout. It also
     * configures the "remember me" functionality and sets up the {@link PersistentTokenRepository} for
     * token management. Additionally, it includes commented-out configurations for custom filters and
     * session management.
     * </p>
     *
     * @param http               the {@link HttpSecurity} object for configuring HTTP security
     * @return the {@link SecurityFilterChain} instance with the configured security settings
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http/*,
                                                   UserDetailsService userDetailsService*/) throws Exception {
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

    /**
     * Defines a {@link UserDetailsService} bean for loading user-specific data by email.
     * <p>
     * The {@link UserDetailsService} is used to retrieve user details for authentication. This implementation
     * looks up users by their email and throws an exception if the user is not found.
     * </p>
     *
     * @param userService the {@link UserService} for accessing user data
     * @return a {@link UserDetailsService} instance configured to load users by email
     */
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return email -> userService.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' not found"));
    }

    /**
     * Defines a {@link PersistentTokenRepository} bean for managing "remember me" tokens.
     * <p>
     * The {@link JdbcTokenRepositoryImpl} is used to store and retrieve persistent login tokens in the
     * database. It requires a {@link DataSource} to interact with the database.
     * </p>
     *
     * @return a {@link PersistentTokenRepository} instance configured with the {@link DataSource}
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }
}