package com.examendu.peliculas.service;

import com.examendu.peliculas.dto.*;

import java.util.List;
import java.util.UUID;

public interface PeliculaService {

    List<PeliculaResponseDTO> findAll();

    PeliculaResponseDTO findById(UUID id);

    PeliculaResponseDTO create(PeliculaRequestDTO requestDTO);

    PeliculaResponseDTO update(UUID id, PeliculaRequestDTO requestDTO);

    void delete(UUID id);

    PeliculaConGenerosResponseDTO createPeliculaConGeneros(PeliculaConGenerosRequestDTO requestDTO);
}
