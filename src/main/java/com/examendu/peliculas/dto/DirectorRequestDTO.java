package com.examendu.peliculas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorRequestDTO {

    @NotBlank(message = "El nombre del director es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 100, message = "La nacionalidad no puede exceder 100 caracteres")
    private String nacionalidad;
}
