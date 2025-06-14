package com.clinic.cita_service.client;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "personal-service", url = "${feign.client.config.personal-service.url}")
public interface PersonalClient {

    @GetMapping("/api/doctores/{doctorId}/disponible")
    @CircuitBreaker(name = "personalService", fallbackMethod = "checkDoctorDisponibleFallback")
    boolean checkDoctorDisponible(@PathVariable Long doctorId);

    @GetMapping("/api/doctores/{doctorId}/especialidad")
    @CircuitBreaker(name = "personalService", fallbackMethod = "getEspecialidadDoctorFallback")
    Long getEspecialidadDoctor(@PathVariable Long doctorId);

    default boolean checkDoctorDisponibleFallback(Long doctorId, Throwable throwable) {
        // Considerar como disponible en caso de fallo
        return true;
    }

    default Long getEspecialidadDoctorFallback(Long doctorId, Throwable throwable) {
        // Devolver null en caso de fallo
        return null;
    }
}