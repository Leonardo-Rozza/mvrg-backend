package com.leonardorozza.mvgrreparacionesbackend.service.impl;


import com.leonardorozza.mvgrreparacionesbackend.exceptions.ResourceNotFoundException;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Reparacion;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Repuesto;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.ReparacionRepository;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.RepuestoRepository;
import com.leonardorozza.mvgrreparacionesbackend.service.RepuestoService;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.repuesto.RepuestoRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.repuesto.RepuestoResponseDTO;
import com.leonardorozza.mvgrreparacionesbackend.utils.mapper.RepuestoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepuestoServiceImpl implements RepuestoService {

    private final RepuestoRepository repuestoRepository;
    private final ReparacionRepository reparacionRepository;
    private final RepuestoMapper repuestoMapper;

    // =====================================================
    // CREAR
    // =====================================================
    @Override
    public RepuestoResponseDTO crear(RepuestoRequestDTO request) {

        Reparacion reparacion = null;

        if (request.getReparacionId() != null) {
            reparacion = reparacionRepository.findById(request.getReparacionId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Reparación no encontrada con ID: " + request.getReparacionId()
                    ));
        }

        Repuesto repuesto = repuestoMapper.toEntity(request);
        repuesto.setReparacion(reparacion);

        return repuestoMapper.toDTO(repuestoRepository.save(repuesto));
    }

    // =====================================================
    // ACTUALIZAR
    // =====================================================
    @Override
    public RepuestoResponseDTO actualizar(Long id, RepuestoRequestDTO request) {

        Repuesto repuesto = repuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con ID: " + id));

        Reparacion reparacion = null;
        if (request.getReparacionId() != null) {
            reparacion = reparacionRepository.findById(request.getReparacionId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Reparación no encontrada con ID: " + request.getReparacionId()
                    ));
        }

        repuesto.setNombre(request.getNombre());
        repuesto.setDescripcion(request.getDescripcion());
        repuesto.setPrecio(request.getPrecio());
        repuesto.setReparacion(reparacion);

        return repuestoMapper.toDTO(repuestoRepository.save(repuesto));
    }

    // =====================================================
    // OBTENER POR ID
    // =====================================================
    @Override
    public RepuestoResponseDTO obtenerPorId(Long id) {
        return repuestoRepository.findById(id)
                .map(repuestoMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con ID: " + id));
    }

    // =====================================================
    // LISTAR TODOS
    // =====================================================
    @Override
    public List<RepuestoResponseDTO> listar() {
        return repuestoRepository.findAll()
                .stream()
                .map(repuestoMapper::toDTO)
                .toList();
    }

    // =====================================================
    // LISTAR POR REPARACION
    // =====================================================
    @Override
    public List<RepuestoResponseDTO> listarPorReparacion(Long reparacionId) {

        if (!reparacionRepository.existsById(reparacionId)) {
            throw new ResourceNotFoundException("Reparación no encontrada con ID: " + reparacionId);
        }

        return repuestoRepository.findByReparacionId(reparacionId)
                .stream()
                .map(repuestoMapper::toDTO)
                .toList();
    }

    // =====================================================
    // ELIMINAR
    // =====================================================
    @Override
    public void eliminar(Long id) {
        Repuesto repuesto = repuestoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repuesto no encontrado con ID: " + id));

        repuestoRepository.delete(repuesto);
    }
}

