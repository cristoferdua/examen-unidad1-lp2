package com.examendu.peliculas.controller;

import com.examendu.peliculas.dto.GeneroRequestDTO;
import com.examendu.peliculas.dto.GeneroResponseDTO;
import com.examendu.peliculas.service.GeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroResponseDTO>> findAll() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(generoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GeneroResponseDTO> create(@Valid @RequestBody GeneroRequestDTO requestDTO) {
        GeneroResponseDTO created = generoService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroResponseDTO> update(@PathVariable UUID id,
                                                     @Valid @RequestBody GeneroRequestDTO requestDTO) {
        return ResponseEntity.ok(generoService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        generoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
