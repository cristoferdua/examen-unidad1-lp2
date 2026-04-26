package com.examendu.peliculas.service;

import com.examendu.peliculas.dto.DirectorRequestDTO;
import com.examendu.peliculas.dto.DirectorResponseDTO;
import com.examendu.peliculas.entity.Director;
import com.examendu.peliculas.exception.ResourceNotFoundException;
import com.examendu.peliculas.mapper.DirectorMapper;
import com.examendu.peliculas.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    @Transactional(readOnly = true)
    public List<DirectorResponseDTO> findAll() {
        return directorMapper.toResponseDTOList(directorRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public DirectorResponseDTO findById(UUID id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con ID: " + id));
        return directorMapper.toResponseDTO(director);
    }

    @Override
    @Transactional
    public DirectorResponseDTO create(DirectorRequestDTO requestDTO) {
        Director director = directorMapper.toEntity(requestDTO);
        director = directorRepository.save(director);
        return directorMapper.toResponseDTO(director);
    }

    @Override
    @Transactional
    public DirectorResponseDTO update(UUID id, DirectorRequestDTO requestDTO) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con ID: " + id));
        directorMapper.updateEntityFromDTO(requestDTO, director);
        director = directorRepository.save(director);
        return directorMapper.toResponseDTO(director);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!directorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Director no encontrado con ID: " + id);
        }
        directorRepository.deleteById(id);
    }
}
