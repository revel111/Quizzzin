package com.example.quizzzin.configurations;

import com.example.quizzzin.models.dto.other.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class EmailPasswordAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getServletPath() + " " + request.getMethod());
        if ("/login".equals(request.getServletPath()) &&
                HttpMethod.POST.matches(request.getMethod())) {
            CredentialsDTO credentialsDTO = MAPPER.readValue(request.getInputStream(), CredentialsDTO.class);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            credentialsDTO.email(), credentialsDTO.password()
                    ));
        }

        filterChain.doFilter(request, response);
    }
}