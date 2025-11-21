package com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente;

import lombok.Data;

@Data
public class ClienteResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
}