package com.clinic.Franquicia_service.Controller;
import com.clinic.Franquicia_service.domain.Clinica;
import com.clinic.Franquicia_service.domain.Sede;
import com.clinic.Franquicia_service.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/clinicas")
@RequiredArgsConstructor
public class ClinicaController {
    private final ClinicaService clinicaService;



    @GetMapping
    public ResponseEntity<List<Clinica>> getAllClinicas() {
        return ResponseEntity.ok(clinicaService.getAllClinicas()); // Devuelve todas las clínicas
    }

    @PostMapping
    public ResponseEntity<Clinica> createClinica(@RequestBody Clinica clinica) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clinicaService.saveClinica(clinica)); // Guardar la clínica
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinica(@PathVariable Long id) {
        clinicaService.deleteClinica(id); // Elimina la clínica por el ID proporcionado
        return ResponseEntity.noContent().build(); // Respuesta exitosa sin contenido
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinica> updateClinica(@PathVariable Long id, @RequestBody Clinica clinicaDetails) {
        Clinica updatedClinica = clinicaService.updateClinica(id, clinicaDetails);
        return ResponseEntity.ok(updatedClinica); // Actualizar la clínica
    }

    @GetMapping("/sedes")
    public ResponseEntity<List<Sede>> getSedes() {
        return ResponseEntity.ok(clinicaService.getSedes()); // Obtener las sedes de la única clínica
    }
}