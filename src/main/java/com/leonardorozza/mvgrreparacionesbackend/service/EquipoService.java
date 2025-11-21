package com.leonardorozza.mvgrreparacionesbackend.service;


import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoResponseDTO;

import java.util.List;

public interface EquipoService {
    EquipoResponseDTO crear(EquipoRequestDTO request);

    EquipoResponseDTO actualizar(Long id, EquipoRequestDTO request);

    EquipoResponseDTO obtenerPorId(Long id);

    List<EquipoResponseDTO> listar();

    List<EquipoResponseDTO> listarPorCliente(Long clienteId);

    void eliminar(Long id);
}
