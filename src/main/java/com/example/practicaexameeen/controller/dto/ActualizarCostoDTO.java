package com.example.practicaexameeen.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarCostoDTO {

    @NotNull(message = "no se permiten id vacias")
    private Long id;
    @NotNull(message = "no se permiten costos vacios ")
    private double nuevoCosto;
}
