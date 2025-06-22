package com.clinic.Franquicia_service.repository;


import com.clinic.Franquicia_service.domain.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {
    Optional<Clinica> findByEmail(String email);
}