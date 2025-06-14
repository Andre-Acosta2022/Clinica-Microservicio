package com.clinic.cita_service.repository;

import com.clinic.cita_service.domain.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorIdAndFechaCita(Long doctorId, LocalDate fechaCita);

    @Query("SELECT c FROM Cita c WHERE c.doctorId = :doctorId AND c.fechaCita = :fecha " +
            "AND ((c.horaCita <= :horaFin AND ADDTIME(c.horaCita, SEC_TO_TIME(c.duracionMinutos * 60)) >= :horaInicio))")
    List<Cita> findConflictingCitas(Long doctorId, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin);

    List<Cita> findByPacienteId(Long pacienteId);
}