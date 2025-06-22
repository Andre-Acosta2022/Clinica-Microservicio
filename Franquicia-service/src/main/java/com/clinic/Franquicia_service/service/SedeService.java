package com.clinic.Franquicia_service.service;
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
public class SedeService {
    private final SedeRepository sedeRepository;
    private final ClinicaRepository clinicaRepository;

    // Guardar una sede asociada a la clínica
    @Transactional
    public Sede saveSede(Sede sede) {
        Optional<Clinica> clinicaOptional = clinicaRepository.findById(sede.getClinica().getId());
        if (clinicaOptional.isEmpty()) {
            throw new ResourceNotFoundException("La clínica con ID " + sede.getClinica().getId() + " no existe.");
        }

        sede.setClinica(clinicaOptional.get()); // Asignar la clínica a la sede
        return sedeRepository.save(sede);
    }

    // Eliminar una sede
    public void deleteSede(Long id) {
        sedeRepository.deleteById(id);
    }

    public List<Sede> findAllSedes() {
        return sedeRepository.findByClinicaId(14L); // Retorna solo las sedes de la clínica 1
    }
}