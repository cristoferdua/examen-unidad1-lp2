package com.examendu.peliculas.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaConGenerosResponseDTO {

    private UUID id;
    private String titulo;
    private String sinopsis;
    private Integer anyoEstreno;
    private Integer duracionMinutos;
    private String clasificacion;
    private String nombreDirector;
    private List<String> generos;
}
