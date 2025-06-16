package com.clinic.HistorialClinica_service.DTO;

import com.clinic.HistorialClinica_service.domain.RegistroMedico;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NuevoRegistroDTO {
    @NotNull
    private Long pacienteId;

    @NotNull
    private RegistroMedico.TipoRegistro tipo;

    @NotNull
    private Long doctorId;

    private Long citaId;
    private String observaciones;
    private String datosEspecificos;
    // Campo faltante añadido
    private LocalDateTime fechaRegistro;
}