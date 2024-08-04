package com.example.quizzzin.configurations;

import com.example.quizzzin.models.dto.other.CredentialsDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import com.example.quizzzin.models.entities.User;

/**
 * DON'T USE!
 * @deprecated
 */
@AllArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {
    private final AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = null;
        if (authentication instanceof UsernamePasswordAuthenticationToken)
            user = authenticationService.authenticate(
                    new CredentialsDTO(
                            (String) authentication.getPrincipal(),
                            (String) authentication.getCredentials()));
        else if (authentication instanceof PreAuthenticatedAuthenticationToken)
            user = authenticationService.findByToken((String) authentication.getPrincipal());

        if (user == null)
            return null;

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}