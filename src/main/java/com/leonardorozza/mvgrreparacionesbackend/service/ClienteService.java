package com.leonardorozza.mvgrreparacionesbackend.service;


import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {

    ClienteResponseDTO crear(ClienteRequestDTO request);

    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO request);

    ClienteResponseDTO obtenerPorId(Long id);

    List<ClienteResponseDTO> listar();

    void eliminar(Long id);
}
