package com.examendu.peliculas.service;

import com.examendu.peliculas.dto.DirectorRequestDTO;
import com.examendu.peliculas.dto.DirectorResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DirectorService {

    List<DirectorResponseDTO> findAll();

    DirectorResponseDTO findById(UUID id);

    DirectorResponseDTO create(DirectorRequestDTO requestDTO);

    DirectorResponseDTO update(UUID id, DirectorRequestDTO requestDTO);

    void delete(UUID id);
}
