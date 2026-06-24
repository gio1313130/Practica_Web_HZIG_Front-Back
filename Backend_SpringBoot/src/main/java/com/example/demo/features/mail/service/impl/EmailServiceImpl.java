package com.example.demo.features.mail.service.impl;

import com.example.demo.features.mail.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorreoElectronico(String to, String subject, String text) {

        try {
            MimeMessage mensaje = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    mensaje,
                    false,
                    "UTF-8"
            );

            helper.setFrom(
                    "noreply@gmail.com",
                    "Envío de correos vía Spring"
            );

            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setTo(to);

            helper.setCc("gio13.hedz@gmail.com");

            mailSender.send(mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
