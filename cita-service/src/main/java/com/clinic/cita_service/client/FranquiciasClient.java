package com.clinic.cita_service.client;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "clinicas-service", url = "${feign.client.config.clinicas-service.url}")
public interface FranquiciasClient {

    @GetMapping("/api/sedes/{sedeId}/disponible")
    @CircuitBreaker(name = "clinicasService", fallbackMethod = "checkSedeDisponibleFallback")
    boolean checkSedeDisponible(@PathVariable Long sedeId);

    default boolean checkSedeDisponibleFallback(Long sedeId, Throwable throwable) {
        // Lógica de fallback: considerar como disponible para no bloquear operaciones
        return true;
    }
}