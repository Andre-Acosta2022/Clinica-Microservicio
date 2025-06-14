package com.clinic.pacientes_service.repository;

import com.clinic.pacientes_service.domain.SeguroMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroMedicoRepository extends JpaRepository<SeguroMedico, Long> {
}