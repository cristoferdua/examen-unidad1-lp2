package com.examendu.peliculas.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneroResponseDTO {

    private UUID id;
    private String nombre;
    private String descripcion;
}
