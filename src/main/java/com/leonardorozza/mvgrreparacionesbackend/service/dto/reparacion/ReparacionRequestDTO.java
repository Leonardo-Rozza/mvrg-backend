package com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion;

import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.enums.EstadoReparacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReparacionRequestDTO {

    @NotNull
    private Long equipoId;

    @NotBlank
    private String descripcionProblema;

    private EstadoReparacion estado;

    private BigDecimal precioEstimado;

    private BigDecimal precioFinal;

    private LocalDate fechaIngreso;

    private LocalDate fechaEstimadaEntrega;

    private LocalDate fechaEntrega;
}
