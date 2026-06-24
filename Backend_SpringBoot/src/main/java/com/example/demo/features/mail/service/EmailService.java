package com.example.demo.features.mail.service;

public interface EmailService {
    public void enviarCorreoElectronico(
            String to,
            String subject,
            String text);
}
