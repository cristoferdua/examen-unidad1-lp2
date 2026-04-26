package com.examendu.peliculas.mapper;

import com.examendu.peliculas.dto.PeliculaRequestDTO;
import com.examendu.peliculas.dto.PeliculaResponseDTO;
import com.examendu.peliculas.entity.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PeliculaMapper {

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "peliculaGeneros", ignore = true)
    Pelicula toEntity(PeliculaRequestDTO requestDTO);

    @Mapping(source = "director.id", target = "directorId")
    @Mapping(source = "director.nombre", target = "nombreDirector")
    @Mapping(target = "generos", ignore = true)
    PeliculaResponseDTO toResponseDTO(Pelicula pelicula);

    @Mapping(source = "director.id", target = "directorId")
    @Mapping(source = "director.nombre", target = "nombreDirector")
    @Mapping(target = "generos", ignore = true)
    List<PeliculaResponseDTO> toResponseDTOList(List<Pelicula> peliculas);

    @Mapping(target = "director", ignore = true)
    @Mapping(target = "peliculaGeneros", ignore = true)
    void updateEntityFromDTO(PeliculaRequestDTO requestDTO, @MappingTarget Pelicula pelicula);
}
