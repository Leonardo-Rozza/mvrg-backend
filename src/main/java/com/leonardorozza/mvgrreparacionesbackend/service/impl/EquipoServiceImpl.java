package com.leonardorozza.mvgrreparacionesbackend.service.impl;

import com.leonardorozza.mvgrreparacionesbackend.exceptions.ResourceNotFoundException;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Cliente;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Equipo;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.ClienteRepository;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.EquipoRepository;
import com.leonardorozza.mvgrreparacionesbackend.service.EquipoService;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoResponseDTO;

import com.leonardorozza.mvgrreparacionesbackend.utils.mapper.EquipoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final ClienteRepository clienteRepository;
    private final EquipoMapper equipoMapper;

    @Override
    public EquipoResponseDTO crear(EquipoRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));

        Equipo equipo = equipoMapper.toEntity(request);
        equipo.setCliente(cliente);

        Equipo guardado = equipoRepository.save(equipo);
        return equipoMapper.toDTO(guardado);
    }

    @Override
    public EquipoResponseDTO actualizar(Long id, EquipoRequestDTO request) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con ID: " + id));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));

        equipo.setMarca(request.getMarca());
        equipo.setModelo(request.getModelo());
        equipo.setImei(request.getImei());
        equipo.setCliente(cliente);

        return equipoMapper.toDTO(equipoRepository.save(equipo));
    }

    @Override
    public EquipoResponseDTO obtenerPorId(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con ID: " + id));

        return equipoMapper.toDTO(equipo);
    }

    @Override
    public List<EquipoResponseDTO> listar() {
        return equipoRepository.findAll()
                .stream()
                .map(equipoMapper::toDTO)
                .toList();
    }

    @Override
    public List<EquipoResponseDTO> listarPorCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId);
        }

        return equipoRepository.findByClienteId(clienteId)
                .stream()
                .map(equipoMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con ID: " + id));

        equipoRepository.delete(equipo);
    }
}
