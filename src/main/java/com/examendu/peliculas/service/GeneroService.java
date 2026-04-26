package com.examendu.peliculas.service;

import com.examendu.peliculas.dto.GeneroRequestDTO;
import com.examendu.peliculas.dto.GeneroResponseDTO;

import java.util.List;
import java.util.UUID;

public interface GeneroService {

    List<GeneroResponseDTO> findAll();

    GeneroResponseDTO findById(UUID id);

    GeneroResponseDTO create(GeneroRequestDTO requestDTO);

    GeneroResponseDTO update(UUID id, GeneroRequestDTO requestDTO);

    void delete(UUID id);
}
