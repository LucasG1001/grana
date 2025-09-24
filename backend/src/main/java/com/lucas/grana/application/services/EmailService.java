package com.lucas.grana.application.services;


public interface EmailService {
    public void sendText(String to, String subject, String body);
    public void sendTemplate(String to, String subject, String templateName);
}