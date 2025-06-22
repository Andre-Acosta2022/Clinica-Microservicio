package com.clinic.empleados_service.controller;

import com.clinic.empleados_service.DTO.DoctorDTO;
import com.clinic.empleados_service.DTO.EspecialidadDTO;
import com.clinic.empleados_service.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO createdDoctor = doctorService.createDoctor(doctorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctores() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkDoctorExists(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.existsById(id));
    }

    @GetMapping("/{doctorId}/disponible")
    public ResponseEntity<Boolean> checkDoctorDisponible(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.isDoctorDisponible(doctorId));
    }

    @GetMapping("/{doctorId}/especialidad")
    public ResponseEntity<EspecialidadDTO> getEspecialidadDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.getEspecialidadDTO(doctorId));
    }
    // Método para actualizar un doctor
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Método para eliminar un doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
