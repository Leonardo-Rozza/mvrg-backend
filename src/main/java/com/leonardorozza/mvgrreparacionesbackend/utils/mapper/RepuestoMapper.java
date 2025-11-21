package com.leonardorozza.mvgrreparacionesbackend.utils.mapper;

import com.leonardorozza.mvgrreparacionesbackend.persistence.entity.Repuesto;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.repuesto.RepuestoRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.repuesto.RepuestoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RepuestoMapper {

    @Mapping(target = "reparacion.id", source = "reparacionId")
    Repuesto toEntity(RepuestoRequestDTO dto);

    @Mapping(target = "reparacionId", source = "reparacion.id")
    RepuestoResponseDTO toDTO(Repuesto entity);
}