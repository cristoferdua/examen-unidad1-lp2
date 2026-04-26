package com.examendu.peliculas.service;

import com.examendu.peliculas.dto.*;
import com.examendu.peliculas.entity.Director;
import com.examendu.peliculas.entity.Genero;
import com.examendu.peliculas.entity.Pelicula;
import com.examendu.peliculas.entity.PeliculaGenero;
import com.examendu.peliculas.exception.ResourceNotFoundException;
import com.examendu.peliculas.mapper.PeliculaMapper;
import com.examendu.peliculas.repository.DirectorRepository;
import com.examendu.peliculas.repository.GeneroRepository;
import com.examendu.peliculas.repository.PeliculaGeneroRepository;
import com.examendu.peliculas.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorRepository directorRepository;
    private final GeneroRepository generoRepository;
    private final PeliculaGeneroRepository peliculaGeneroRepository;
    private final PeliculaMapper peliculaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PeliculaResponseDTO> findAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculaResponseDTO> responseList = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            PeliculaResponseDTO dto = peliculaMapper.toResponseDTO(pelicula);
            dto.setGeneros(obtenerNombresGeneros(pelicula));
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    @Transactional(readOnly = true)
    public PeliculaResponseDTO findById(UUID id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con ID: " + id));
        PeliculaResponseDTO dto = peliculaMapper.toResponseDTO(pelicula);
        dto.setGeneros(obtenerNombresGeneros(pelicula));
        return dto;
    }

    @Override
    @Transactional
    public PeliculaResponseDTO create(PeliculaRequestDTO requestDTO) {
        Director director = directorRepository.findById(requestDTO.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con ID: " + requestDTO.getDirectorId()));

        Pelicula pelicula = peliculaMapper.toEntity(requestDTO);
        pelicula.setDirector(director);
        pelicula = peliculaRepository.save(pelicula);

        PeliculaResponseDTO dto = peliculaMapper.toResponseDTO(pelicula);
        dto.setGeneros(new ArrayList<>());
        return dto;
    }

    @Override
    @Transactional
    public PeliculaResponseDTO update(UUID id, PeliculaRequestDTO requestDTO) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada con ID: " + id));

        Director director = directorRepository.findById(requestDTO.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con ID: " + requestDTO.getDirectorId()));

        peliculaMapper.updateEntityFromDTO(requestDTO, pelicula);
        pelicula.setDirector(director);
        pelicula = peliculaRepository.save(pelicula);

        PeliculaResponseDTO dto = peliculaMapper.toResponseDTO(pelicula);
        dto.setGeneros(obtenerNombresGeneros(pelicula));
        return dto;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!peliculaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Película no encontrada con ID: " + id);
        }
        peliculaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PeliculaConGenerosResponseDTO createPeliculaConGeneros(PeliculaConGenerosRequestDTO requestDTO) {
        // Buscar director
        Director director = directorRepository.findById(requestDTO.getDirectorId())
                .orElseThrow(() -> new ResourceNotFoundException("Director no encontrado con ID: " + requestDTO.getDirectorId()));

        // Crear película
        Pelicula pelicula = Pelicula.builder()
                .titulo(requestDTO.getTitulo())
                .sinopsis(requestDTO.getSinopsis())
                .anyoEstreno(requestDTO.getAnyoEstreno())
                .duracionMinutos(requestDTO.getDuracionMinutos())
                .clasificacion(requestDTO.getClasificacion())
                .director(director)
                .peliculaGeneros(new ArrayList<>())
                .build();
        pelicula = peliculaRepository.save(pelicula);

        // Asignar géneros
        List<String> nombresGeneros = new ArrayList<>();
        for (UUID generoId : requestDTO.getGeneroIds()) {
            Genero genero = generoRepository.findById(generoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Género no encontrado con ID: " + generoId));

            PeliculaGenero peliculaGenero = PeliculaGenero.builder()
                    .pelicula(pelicula)
                    .genero(genero)
                    .build();
            peliculaGeneroRepository.save(peliculaGenero);
            nombresGeneros.add(genero.getNombre());
        }

        // Construir respuesta
        return PeliculaConGenerosResponseDTO.builder()
                .id(pelicula.getId())
                .titulo(pelicula.getTitulo())
                .sinopsis(pelicula.getSinopsis())
                .anyoEstreno(pelicula.getAnyoEstreno())
                .duracionMinutos(pelicula.getDuracionMinutos())
                .clasificacion(pelicula.getClasificacion())
                .nombreDirector(director.getNombre())
                .generos(nombresGeneros)
                .build();
    }

    private List<String> obtenerNombresGeneros(Pelicula pelicula) {
        List<String> nombres = new ArrayList<>();
        for (PeliculaGenero pg : pelicula.getPeliculaGeneros()) {
            nombres.add(pg.getGenero().getNombre());
        }
        return nombres;
    }
}
