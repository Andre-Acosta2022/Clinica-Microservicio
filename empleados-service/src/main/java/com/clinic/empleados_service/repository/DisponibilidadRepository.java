package com.clinic.empleados_service.repository;

import com.clinic.empleados_service.domain.Disponibilidad;
import com.clinic.empleados_service.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {
    List<Disponibilidad> findByDoctor(Doctor doctor);
}