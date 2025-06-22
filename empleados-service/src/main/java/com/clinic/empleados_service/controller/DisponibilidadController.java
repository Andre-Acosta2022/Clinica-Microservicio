package com.clinic.empleados_service.controller;
import com.clinic.empleados_service.DTO.DisponibilidadDTO;
import com.clinic.empleados_service.service.DisponibilidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadController {

    private final DisponibilidadService disponibilidadService;

    @PostMapping
    public ResponseEntity<DisponibilidadDTO> createDisponibilidad(@Valid @RequestBody DisponibilidadDTO disponibilidadDTO) {
        DisponibilidadDTO createdDisponibilidad = disponibilidadService.createDisponibilidad(disponibilidadDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDisponibilidad);
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadDTO>> getAllDisponibilidades() {
        return ResponseEntity.ok(disponibilidadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadDTO> getDisponibilidadById(@PathVariable Long id) {
        return ResponseEntity.ok(disponibilidadService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadDTO> updateDisponibilidad(@PathVariable Long id, @Valid @RequestBody DisponibilidadDTO disponibilidadDTO) {
        DisponibilidadDTO updatedDisponibilidad = disponibilidadService.updateDisponibilidad(id, disponibilidadDTO);
        return ResponseEntity.ok(updatedDisponibilidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Long id) {
        disponibilidadService.deleteDisponibilidad(id);
        return ResponseEntity.noContent().build();
    }
}
