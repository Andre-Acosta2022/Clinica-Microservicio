package com.clinic.notificacionesservice.service;

import com.clinic.notificacionesservice.domain.Notificacion;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void enviarEmail(Notificacion notificacion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notificacion.getDestinatarioId());
        message.setSubject(notificacion.getAsunto());
        message.setText(notificacion.getContenido());
        mailSender.send(message);
    }
}
