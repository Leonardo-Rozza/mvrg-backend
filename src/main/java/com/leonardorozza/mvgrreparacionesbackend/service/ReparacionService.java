package com.leonardorozza.mvgrreparacionesbackend.service;

import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.enums.EstadoReparacion;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion.ReparacionRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion.ReparacionResponseDTO;

import java.util.List;

public interface ReparacionService {

        ReparacionResponseDTO crear(ReparacionRequestDTO request);

        ReparacionResponseDTO actualizar(Long id, ReparacionRequestDTO request);

        ReparacionResponseDTO cambiarEstado(Long id, EstadoReparacion nuevoEstado);

        ReparacionResponseDTO obtenerPorId(Long id);

        List<ReparacionResponseDTO> listar();

        List<ReparacionResponseDTO> listarPorEquipo(Long equipoId);

        List<ReparacionResponseDTO> listarPorEstado(EstadoReparacion estado);

        void eliminar(Long id);

}
