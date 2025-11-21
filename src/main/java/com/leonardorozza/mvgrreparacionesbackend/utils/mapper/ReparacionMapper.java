package com.leonardorozza.mvgrreparacionesbackend.utils.mapper;

import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Reparacion;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion.ReparacionRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.reparacion.ReparacionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReparacionMapper {

    @Mapping(target = "equipo.id", source = "equipoId")
    Reparacion toEntity(ReparacionRequestDTO dto);

    @Mapping(target = "equipoId", source = "equipo.id")
    ReparacionResponseDTO toResponse(Reparacion entity);
}