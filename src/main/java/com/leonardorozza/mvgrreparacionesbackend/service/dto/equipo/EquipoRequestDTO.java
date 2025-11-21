package com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EquipoRequestDTO {

    @NotBlank
    @Size(max = 60)
    private String marca;

    @NotBlank
    @Size(max = 60)
    private String modelo;

    @Size(max = 30)
    private String imei;

    @Size(max = 40)
    private String color;

    @Size(max = 255)
    private String descripcion;

    @NotNull
    private Long clienteId;
}
