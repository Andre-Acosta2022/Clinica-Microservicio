package com.clinic.HistorialClinica_service.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArchivoResponseDTO {
    private Long id;
    private String nombreArchivo;
    private String tipoMime;
    private String urlAlmacenamiento;
    private LocalDateTime fechaSubida;
}