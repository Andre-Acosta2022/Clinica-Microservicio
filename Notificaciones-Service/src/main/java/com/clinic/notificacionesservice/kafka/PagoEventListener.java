package com.clinic.notificacionesservice.kafka;

import com.clinic.notificacionesservice.DTO.NotificacionDTO;
import com.clinic.notificacionesservice.DTO.PagoEvent;
import com.clinic.notificacionesservice.domain.enums.TipoNotificaciòn;
import com.clinic.notificacionesservice.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagoEventListener {

    private final NotificacionService notificacionService;

    @KafkaListener(topics = "pagos-topic", groupId = "notification-group")
    public void handlePagoEvent(PagoEvent event) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setTipo(TipoNotificaciòn.ESTADO_PAGO);
        dto.setDestinatarioId(event.getPacienteId().toString());
        // ... set otros campos según estado de pago
        notificacionService.crearNotificacion(dto);
    }
}
