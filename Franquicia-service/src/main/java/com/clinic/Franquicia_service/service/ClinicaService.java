package com.clinic.Franquicia_service.service;
import com.clinic.Franquicia_service.Client.CitasClient;
import com.clinic.Franquicia_service.domain.Clinica;
import com.clinic.Franquicia_service.domain.Sede;
import com.clinic.Franquicia_service.exception.ResourceNotFoundException;
import com.clinic.Franquicia_service.repository.ClinicaRepository;
import com.clinic.Franquicia_service.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicaService {
    private final ClinicaRepository clinicaRepository;
    private final SedeRepository sedeRepository;
    private final CitasClient citasClient;

    // Método para obtener la clínica (solo una clínica en este caso)
    public Clinica getClinica() {
        return clinicaRepository.findById(14L)
                .orElseThrow(() -> new ResourceNotFoundException("Clinica no encontrada"));
    }
    public List<Clinica> getAllClinicas() {
        return clinicaRepository.findAll(); // Devuelve todas las clínicas
    }
    // Método para guardar una nueva clínica
    @Transactional
    public Clinica saveClinica(Clinica clinica) {
        if (!isEmailUnique(clinica.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }
        return clinicaRepository.save(clinica);
    }

    @Transactional
    public void deleteClinica(Long id) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clinica no encontrada con ID: " + id)); // Lanzar una excepción si no se encuentra la clínica
        clinicaRepository.delete(clinica); // Eliminar la clínica
    }

    // Método para obtener las sedes asociadas a la clínica
    public List<Sede> getSedes() {
        return sedeRepository.findByClinicaId(14L); // Siempre obtiene las sedes de la clínica 1
    }

    // Método para actualizar la clínica
    @Transactional
    public Clinica updateClinica(Long id, Clinica clinicaDetails) {
        Clinica clinica = clinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clinica no encontrada con ID: " + id));

        // Actualizando los detalles de la clínica
        clinica.setNombre(clinicaDetails.getNombre());
        clinica.setDireccion(clinicaDetails.getDireccion());
        clinica.setTelefono(clinicaDetails.getTelefono());
        clinica.setEmail(clinicaDetails.getEmail());
        clinica.setResponsable(clinicaDetails.getResponsable());

        // Guardando la clínica actualizada
        return clinicaRepository.save(clinica);
    }
    public boolean isEmailUnique(String email) {
        return clinicaRepository.findByEmail(email).isEmpty();
    }
}