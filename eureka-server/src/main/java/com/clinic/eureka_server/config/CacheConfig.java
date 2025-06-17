package com.clinic.eureka_server.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(60, java.util.concurrent.TimeUnit.SECONDS) // Define el tiempo de expiración de la caché
                .maximumSize(100)); // Limita la cantidad de elementos en caché
        return cacheManager;
    }
}
