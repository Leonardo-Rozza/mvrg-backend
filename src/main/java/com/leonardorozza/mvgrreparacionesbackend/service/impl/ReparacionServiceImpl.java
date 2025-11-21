package com.leonardorozza.mvgrreparacionesbackend.service.impl;

import com.leonardorozza.mvgrreparacionesbackend.exceptions.ResourceNotFoundException;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Equipo;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Reparacion;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.enums.EstadoReparacion;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.EquipoRepository;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.ReparacionRepository;
import com.leonardorozza.mvgrreparacionesbackend.service.ReparacionService;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion.ReparacionRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion.ReparacionResponseDTO;
import com.leonardorozza.mvgrreparacionesbackend.utils.mapper.ReparacionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReparacionServiceImpl implements ReparacionService {

    private final ReparacionRepository reparacionRepository;
    private final EquipoRepository equipoRepository;
    private final ReparacionMapper reparacionMapper;

    @Override
    public ReparacionResponseDTO crear(ReparacionRequestDTO request) {

        Equipo equipo = equipoRepository.findById(request.getEquipoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipo no encontrado con ID: " + request.getEquipoId()));

        Reparacion reparacion = reparacionMapper.toEntity(request);
        reparacion.setEquipo(equipo);

        // Si el usuario no envía estado → lo seteamos por defecto
        if (request.getEstado() == null) {
            reparacion.setEstado(EstadoReparacion.EN_PROCESO);
        }

        Reparacion guardada = reparacionRepository.save(reparacion);

        return reparacionMapper.toDTO(guardada);
    }

    @Override
    public ReparacionResponseDTO actualizar(Long id, ReparacionRequestDTO request) {

        Reparacion reparacion = reparacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reparación no encontrada con ID: " + id));

        Equipo equipo = equipoRepository.findById(request.getEquipoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipo no encontrado con ID: " + request.getEquipoId()));

        // Actualizamos los datos según tu DTO
        reparacion.setEquipo(equipo);
        reparacion.setDescripcionProblema(request.getDescripcionProblema());
        reparacion.setEstado(request.getEstado());
        reparacion.setPrecioEstimado(request.getPrecioEstimado());
        reparacion.setPrecioFinal(request.getPrecioFinal());
        reparacion.setFechaIngreso(request.getFechaIngreso());
        reparacion.setFechaEstimadaEntrega(request.getFechaEstimadaEntrega());
        reparacion.setFechaEntrega(request.getFechaEntrega());

        return reparacionMapper.toDTO(reparacionRepository.save(reparacion));
    }

    @Override
    public ReparacionResponseDTO cambiarEstado(Long id, EstadoReparacion nuevoEstado) {

        Reparacion reparacion = reparacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reparación no encontrada con ID: " + id));

        reparacion.setEstado(nuevoEstado);

        return reparacionMapper.toDTO(reparacionRepository.save(reparacion));
    }

    @Override
    public ReparacionResponseDTO obtenerPorId(Long id) {
        Reparacion reparacion = reparacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reparación no encontrada con ID: " + id));

        return reparacionMapper.toDTO(reparacion);
    }

    @Override
    public List<ReparacionResponseDTO> listar() {
        return reparacionRepository.findAll()
                .stream()
                .map(reparacionMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReparacionResponseDTO> listarPorEquipo(Long equipoId) {

        if (!equipoRepository.existsById(equipoId)) {
            throw new ResourceNotFoundException("Equipo no encontrado con ID: " + equipoId);
        }

        return reparacionRepository.findByEquipoId(equipoId)
                .stream()
                .map(reparacionMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReparacionResponseDTO> listarPorEstado(EstadoReparacion estado) {

        return reparacionRepository.findByEstado(estado)
                .stream()
                .map(reparacionMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminar(Long id) {

        Reparacion reparacion = reparacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reparación no encontrada con ID: " + id));

        reparacionRepository.delete(reparacion);
    }
}
