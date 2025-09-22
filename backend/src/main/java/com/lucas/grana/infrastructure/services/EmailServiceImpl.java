package com.lucas.grana.infrastructure.services;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailServiceImpl {

    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    public void sendText(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("seu-email@dominio.com");
        helper.setText(body, true);
        mailSender.send(message);
    }

    public void sendTemplate(String to, String subject, String templateName, Context context)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlContent = templateEngine.process(templateName, context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("seu-email@dominio.com");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
