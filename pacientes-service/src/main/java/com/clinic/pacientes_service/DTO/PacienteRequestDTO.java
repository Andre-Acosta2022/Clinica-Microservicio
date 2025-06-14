package com.clinic.pacientes_service.DTO;

import com.clinic.pacientes_service.domain.Paciente;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteRequestDTO {
    private String nombre;
    private String apellidos;
    private Integer edad;
    private String email;
    private LocalDate fechaNacimiento;
    private Paciente.Genero sexo;
    private Paciente.EstadoCivil estadoCivil;
    private String telefono;
    private String nacionalidad;
    private Long direccionId;
    private String tipoDocumento;
    private String dni;
    private String contactoEmergencia;
    private Long seguroMedicoId;
}