package com.examendu.peliculas.controller;

import com.examendu.peliculas.dto.DirectorRequestDTO;
import com.examendu.peliculas.dto.DirectorResponseDTO;
import com.examendu.peliculas.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> findAll() {
        return ResponseEntity.ok(directorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(directorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> create(@Valid @RequestBody DirectorRequestDTO requestDTO) {
        DirectorResponseDTO created = directorService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> update(@PathVariable UUID id,
                                                       @Valid @RequestBody DirectorRequestDTO requestDTO) {
        return ResponseEntity.ok(directorService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
