package com.clinic.HistorialClinica_service.service;

import com.clinic.HistorialClinica_service.DTO.ArchivoResponseDTO;
import com.clinic.HistorialClinica_service.domain.ArchivoAdjunto;
import com.clinic.HistorialClinica_service.domain.RegistroMedico;
import com.clinic.HistorialClinica_service.exception.RegistroNotFoundException;
import com.clinic.HistorialClinica_service.repository.ArchivoRepository;
import com.clinic.HistorialClinica_service.repository.RegistroMedicoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArchivoService {

    private final ArchivoRepository archivoRepository;
    private final RegistroMedicoRepository registroMedicoRepository;
    private final AlmacenamientoService almacenamientoService;

    @Transactional
    public ArchivoResponseDTO guardarArchivo(MultipartFile file, Long registroId) throws IOException {
        RegistroMedico registro = registroMedicoRepository.findById(registroId)
                .orElseThrow(() -> new RegistroNotFoundException(
                        "Registro médico no encontrado: " + registroId));

        String urlAlmacenamiento = almacenamientoService.subirArchivo(file);

        ArchivoAdjunto archivo = ArchivoAdjunto.builder()
                .nombreArchivo(file.getOriginalFilename())
                .tipoMime(file.getContentType())
                .urlAlmacenamiento(urlAlmacenamiento)
                .fechaSubida(LocalDateTime.now())
                .registro(registro)
                .build();

        archivo = archivoRepository.save(archivo);
        log.info("Archivo adjunto guardado: {}", archivo.getId());

        return ArchivoResponseDTO.builder()
                .id(archivo.getId())
                .nombreArchivo(archivo.getNombreArchivo())
                .tipoMime(archivo.getTipoMime())
                .urlAlmacenamiento(archivo.getUrlAlmacenamiento())
                .fechaSubida(archivo.getFechaSubida())
                .build();
    }
}