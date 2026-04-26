package com.examendu.peliculas.mapper;

import com.examendu.peliculas.dto.GeneroRequestDTO;
import com.examendu.peliculas.dto.GeneroResponseDTO;
import com.examendu.peliculas.entity.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroResponseDTO toResponseDTO(Genero genero);

    List<GeneroResponseDTO> toResponseDTOList(List<Genero> generos);

    Genero toEntity(GeneroRequestDTO requestDTO);

    void updateEntityFromDTO(GeneroRequestDTO requestDTO, @MappingTarget Genero genero);
}
