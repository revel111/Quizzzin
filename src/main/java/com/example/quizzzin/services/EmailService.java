package com.example.quizzzin.services;

import com.example.quizzzin.models.entities.SecureToken;
import com.example.quizzzin.models.entities.User;
import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    //    private final SpringTemplateEngine springTemplateEngine;
    private final SecureTokenService secureTokenService;
    private final ServletContext servletContext;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${site.base.url}")
    private String siteBaseUrl;

    // ! This method is being user for every single case with sending message,
    // ! cause further there will be distinct methods with it's html parsed files.
    public void sendEmail(User user, String urlPath) {
        SecureToken secureToken = secureTokenService.generateAndSaveSecureToken(user);

        String url = UriComponentsBuilder.fromHttpUrl(siteBaseUrl + servletContext.getContextPath())
                .path(urlPath).queryParam("token", secureToken.getToken()).toUriString();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Email with the link: " + url);
        message.setTo(user.getEmail());
        message.setFrom(sender);
        message.setSubject("Email verification");

        mailSender.send(message);
    }
}