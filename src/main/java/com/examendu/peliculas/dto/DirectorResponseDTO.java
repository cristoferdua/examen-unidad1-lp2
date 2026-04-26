package com.examendu.peliculas.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorResponseDTO {

    private UUID id;
    private String nombre;
    private String nacionalidad;
}
