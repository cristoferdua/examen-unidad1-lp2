package com.examendu.peliculas.controller;

import com.examendu.peliculas.dto.*;
import com.examendu.peliculas.service.PeliculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<PeliculaResponseDTO>> findAll() {
        return ResponseEntity.ok(peliculaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(peliculaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> create(@Valid @RequestBody PeliculaRequestDTO requestDTO) {
        PeliculaResponseDTO created = peliculaService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> update(@PathVariable UUID id,
                                                      @Valid @RequestBody PeliculaRequestDTO requestDTO) {
        return ResponseEntity.ok(peliculaService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/con-generos")
    public ResponseEntity<PeliculaConGenerosResponseDTO> createPeliculaConGeneros(
            @Valid @RequestBody PeliculaConGenerosRequestDTO requestDTO) {
        PeliculaConGenerosResponseDTO created = peliculaService.createPeliculaConGeneros(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}