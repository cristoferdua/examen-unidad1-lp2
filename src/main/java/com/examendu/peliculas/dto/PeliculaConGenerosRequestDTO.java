package com.examendu.peliculas.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeliculaConGenerosRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede exceder 200 caracteres")
    private String titulo;

    @Size(max = 2000, message = "La sinopsis no puede exceder 2000 caracteres")
    private String sinopsis;

    @NotNull(message = "El año de estreno es obligatorio")
    @Min(value = 1888, message = "El año de estreno no puede ser menor a 1888")
    private Integer anyoEstreno;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser mayor a 0")
    private Integer duracionMinutos;

    @Size(max = 20, message = "La clasificación no puede exceder 20 caracteres")
    private String clasificacion;

    @NotNull(message = "El ID del director es obligatorio")
    private UUID directorId;

    @NotEmpty(message = "La película debe tener al menos un género")
    private List<UUID> generoIds;
}
