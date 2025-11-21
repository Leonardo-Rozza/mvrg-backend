package com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank
    @Size(max = 60)
    private String nombre;

    @NotBlank
    @Size(max = 60)
    private String apellido;

    @NotBlank
    @Size(max = 20)
    private String telefono;

    @Email
    @Size(max = 120)
    private String email;

    @Size(max = 255)
    private String direccion;
}