package com.examendu.peliculas.service;

import com.examendu.peliculas.dto.GeneroRequestDTO;
import com.examendu.peliculas.dto.GeneroResponseDTO;
import com.examendu.peliculas.entity.Genero;
import com.examendu.peliculas.exception.ResourceNotFoundException;
import com.examendu.peliculas.mapper.GeneroMapper;
import com.examendu.peliculas.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GeneroResponseDTO> findAll() {
        return generoMapper.toResponseDTOList(generoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public GeneroResponseDTO findById(UUID id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Género no encontrado con ID: " + id));
        return generoMapper.toResponseDTO(genero);
    }

    @Override
    @Transactional
    public GeneroResponseDTO create(GeneroRequestDTO requestDTO) {
        Genero genero = generoMapper.toEntity(requestDTO);
        genero = generoRepository.save(genero);
        return generoMapper.toResponseDTO(genero);
    }

    @Override
    @Transactional
    public GeneroResponseDTO update(UUID id, GeneroRequestDTO requestDTO) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Género no encontrado con ID: " + id));
        generoMapper.updateEntityFromDTO(requestDTO, genero);
        genero = generoRepository.save(genero);
        return generoMapper.toResponseDTO(genero);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!generoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Género no encontrado con ID: " + id);
        }
        generoRepository.deleteById(id);
    }
}
