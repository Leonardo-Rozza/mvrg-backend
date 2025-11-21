package com.leonardorozza.mvgrreparacionesbackend.utils.mapper;

import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Cliente;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.cliente.ClienteResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteRequestDTO dto);

    ClienteResponseDTO toDTO(Cliente entity);

    List<ClienteResponseDTO> toDTOList(List<Cliente> entities);

}