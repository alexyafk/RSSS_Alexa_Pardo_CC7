package com.example.practicaexameeen.controller.dto;

import com.example.practicaexameeen.model.TipoServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdenServicioDTO {

    @NotNull(message = "El id del vehiculo es obligatorio")
    private Long vehiculoId;

    @NotBlank(message = "La descripcon es obligatoria")
    private String descripcion;

    @NotNull(message = "El tipo de servicio es obligatorio")
    private TipoServicio tipo;
}
