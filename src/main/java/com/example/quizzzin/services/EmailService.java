package com.example.quizzzin.services;

import com.example.quizzzin.models.entities.SecureToken;
import com.example.quizzzin.models.entities.User;
import jakarta.annotation.Nonnull;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine springTemplateEngine;
    private final SecureTokenService secureTokenService;
    private final ServletContext servletContext;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${site.base.url}")
    private String siteBaseUrl;

    public void sendEmail(User user, String urlPath, String subject, String templateName, Map<String, Object> model) {
        model.put("url", createUrlAndSaveToken(user, urlPath));

        Context context = new Context();
        context.setVariables(model);

        String htmlContent = springTemplateEngine.process(templateName, context);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(user.getEmail());
            helper.setFrom(sender);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
        } catch (MessagingException e) {
            //! Handle
        }


        mailSender.send(message);
    }

    private String createUrlAndSaveToken(User user, String urlPath) {
        SecureToken secureToken = secureTokenService.generateAndSaveSecureToken(user);

        return UriComponentsBuilder.fromHttpUrl(siteBaseUrl + servletContext.getContextPath())
                .path(urlPath).queryParam("token", secureToken.getToken()).toUriString();
    }
}