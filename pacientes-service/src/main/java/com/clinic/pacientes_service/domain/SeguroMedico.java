package com.clinic.pacientes_service.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seguro_medico")
public class SeguroMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "tipo_seguro", nullable = false)
    private String tipoSeguro;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String cobertura;
}
