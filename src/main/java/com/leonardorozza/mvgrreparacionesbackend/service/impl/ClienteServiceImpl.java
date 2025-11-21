package com.leonardorozza.mvgrreparacionesbackend.service.impl;

import com.leonardorozza.mvgrreparacionesbackend.exceptions.ResourceNotFoundException;
import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Cliente;
import com.leonardorozza.mvgrreparacionesbackend.persistence.repository.ClienteRepository;
import com.leonardorozza.mvgrreparacionesbackend.service.ClienteService;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteResponseDTO;
import com.leonardorozza.mvgrreparacionesbackend.utils.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO request) {
        Cliente cliente = clienteMapper.toEntity(request);
        clienteRepository.save(cliente);
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setNombre(request.getNombre());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setDireccion(request.getDireccion());

        return clienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteResponseDTO obtenerPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .toList();
    }

    @Override
    public void eliminar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        clienteRepository.delete(cliente);
    }
}

