package com.clinic.cita_service.client;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pacientes-service", url = "${feign.client.config.pacientes-service.url}")
public interface PacientesClient {

    @GetMapping("/api/pacientes/{pacienteId}/existe")
    @CircuitBreaker(name = "pacientesService", fallbackMethod = "checkPacienteExisteFallback")
    boolean checkPacienteExiste(@PathVariable Long pacienteId);

    default boolean checkPacienteExisteFallback(Long pacienteId, Throwable throwable) {
        // Asumir que el paciente existe en caso de fallo
        return true;
    }
}