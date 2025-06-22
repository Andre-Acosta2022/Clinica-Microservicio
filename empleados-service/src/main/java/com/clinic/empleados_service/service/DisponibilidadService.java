package com.clinic.empleados_service.service;
import com.clinic.empleados_service.DTO.DisponibilidadDTO;
import com.clinic.empleados_service.domain.Disponibilidad;
import com.clinic.empleados_service.domain.Doctor;
import com.clinic.empleados_service.exception.ResourceNotFoundException;
import com.clinic.empleados_service.repository.DisponibilidadRepository;
import com.clinic.empleados_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisponibilidadService {

    private final DisponibilidadRepository disponibilidadRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public DisponibilidadDTO createDisponibilidad(DisponibilidadDTO disponibilidadDTO) {
        Doctor doctor = doctorRepository.findById(disponibilidadDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con ID: " + disponibilidadDTO.getDoctorId()));

        Disponibilidad disponibilidad = Disponibilidad.builder()
                .doctor(doctor)
                .diaSemana(disponibilidadDTO.getDiaSemana())
                .horaInicio(disponibilidadDTO.getHoraInicio())
                .horaFin(disponibilidadDTO.getHoraFin())
                .build();

        Disponibilidad savedDisponibilidad = disponibilidadRepository.save(disponibilidad);
        return convertToDTO(savedDisponibilidad);
    }

    @Transactional(readOnly = true)
    public List<DisponibilidadDTO> findAll() {
        return disponibilidadRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DisponibilidadDTO findById(Long id) {
        Disponibilidad disponibilidad = disponibilidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disponibilidad no encontrada con ID: " + id));
        return convertToDTO(disponibilidad);
    }

    @Transactional
    public DisponibilidadDTO updateDisponibilidad(Long id, DisponibilidadDTO disponibilidadDTO) {
        Disponibilidad disponibilidad = disponibilidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disponibilidad no encontrada con ID: " + id));

        Doctor doctor = doctorRepository.findById(disponibilidadDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado con ID: " + disponibilidadDTO.getDoctorId()));

        disponibilidad.setDoctor(doctor);
        disponibilidad.setDiaSemana(disponibilidadDTO.getDiaSemana());
        disponibilidad.setHoraInicio(disponibilidadDTO.getHoraInicio());
        disponibilidad.setHoraFin(disponibilidadDTO.getHoraFin());

        Disponibilidad updatedDisponibilidad = disponibilidadRepository.save(disponibilidad);
        return convertToDTO(updatedDisponibilidad);
    }

    @Transactional
    public void deleteDisponibilidad(Long id) {
        Disponibilidad disponibilidad = disponibilidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disponibilidad no encontrada con ID: " + id));
        disponibilidadRepository.delete(disponibilidad);
    }

    private DisponibilidadDTO convertToDTO(Disponibilidad disponibilidad) {
        return DisponibilidadDTO.builder()
                .id(disponibilidad.getId())
                .doctorId(disponibilidad.getDoctor().getId())
                .diaSemana(disponibilidad.getDiaSemana())
                .horaInicio(disponibilidad.getHoraInicio())
                .horaFin(disponibilidad.getHoraFin())
                .build();
    }
}
