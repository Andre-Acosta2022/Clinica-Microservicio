package com.clinic.notificacionesservice.kafka;

import com.clinic.notificacionesservice.DTO.CitaEvent;
import com.clinic.notificacionesservice.DTO.NotificacionDTO;
import com.clinic.notificacionesservice.domain.enums.TipoNotificaciòn;
import com.clinic.notificacionesservice.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitaEventListener {

    private final NotificacionService notificacionService;

    @KafkaListener(topics = "citas-topic", groupId = "notification-group")
    public void handleCitaEvent(CitaEvent event) {
        if("CREATED".equals(event.getTipoEvento())) {
            // Crear notificación para paciente
            NotificacionDTO dto = new NotificacionDTO();
            dto.setTipo(TipoNotificaciòn.CONFIRMACION_CITA);
            dto.setDestinatarioId(event.getPacienteId().toString());
            // ... set otros campos
            notificacionService.crearNotificacion(dto);

            // Crear notificación para doctor
            NotificacionDTO dtoDoctor = new NotificacionDTO();
            // ... similar
            notificacionService.crearNotificacion(dtoDoctor);
        }
        // Manejar otros tipos de eventos
    }
}