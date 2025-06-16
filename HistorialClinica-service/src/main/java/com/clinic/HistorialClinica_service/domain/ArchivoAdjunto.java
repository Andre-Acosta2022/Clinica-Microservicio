package com.clinic.HistorialClinica_service.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "archivos_historial",
        uniqueConstraints = @UniqueConstraint(columnNames = "url_almacenamiento"))
public class ArchivoAdjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArchivo;
    private String tipoMime;
    private String urlAlmacenamiento;
    private LocalDateTime fechaSubida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_id")
    private RegistroMedico registro;
}