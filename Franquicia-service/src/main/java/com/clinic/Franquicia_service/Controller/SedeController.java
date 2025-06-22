package com.clinic.Franquicia_service.Controller;
import com.clinic.Franquicia_service.domain.Clinica;
import com.clinic.Franquicia_service.domain.Sede;
import com.clinic.Franquicia_service.service.ClinicaService;
import com.clinic.Franquicia_service.service.SedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sedes")
@RequiredArgsConstructor
public class SedeController {
    private final SedeService sedeService;

    @GetMapping
    public ResponseEntity<List<Sede>> getAllSedes() {
        return ResponseEntity.ok(sedeService.findAllSedes()); // Solo obtiene las sedes de la única clínica
    }

    @PostMapping
    public ResponseEntity<Sede> createSede(@RequestBody Sede sede) {
        Sede savedSede = sedeService.saveSede(sede); // Asocia la sede a la clínica única
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> updateSede(@PathVariable Long id, @RequestBody Sede sede) {
        sede.setId(id);
        return ResponseEntity.ok(sedeService.saveSede(sede)); // Actualiza la sede
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        sedeService.deleteSede(id); // Elimina la sede
        return ResponseEntity.noContent().build();
    }
}