package com.clinic.HistorialClinica_service.repository;
import com.clinic.HistorialClinica_service.domain.ArchivoAdjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<ArchivoAdjunto, Long> {
}
