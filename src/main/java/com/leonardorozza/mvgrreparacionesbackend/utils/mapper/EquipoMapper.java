package com.leonardorozza.mvgrreparacionesbackend.utils.mapper;


import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Equipo;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipoMapper {

    @Mapping(target = "cliente.id", source = "clienteId")
    Equipo toEntity(EquipoRequestDTO dto);

    @Mapping(target = "clienteId", source = "cliente.id")
    EquipoResponseDTO toResponse(Equipo entity);
}