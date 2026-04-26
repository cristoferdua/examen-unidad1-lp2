package com.examendu.peliculas.mapper;

import com.examendu.peliculas.dto.DirectorRequestDTO;
import com.examendu.peliculas.dto.DirectorResponseDTO;
import com.examendu.peliculas.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorResponseDTO toResponseDTO(Director director);

    List<DirectorResponseDTO> toResponseDTOList(List<Director> directors);

    Director toEntity(DirectorRequestDTO requestDTO);

    void updateEntityFromDTO(DirectorRequestDTO requestDTO, @MappingTarget Director director);
}
