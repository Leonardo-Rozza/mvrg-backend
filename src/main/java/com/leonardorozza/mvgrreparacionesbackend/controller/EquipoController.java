package com.leonardorozza.mvgrreparacionesbackend.controller;

import com.leonardorozza.mvgrreparacionesbackend.service.EquipoService;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoRequestDTO;
import com.leonardorozza.mvgrreparacionesbackend.service.dto.equipo.EquipoResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@Tag(name = "Equipos", description = "Gestión de equipos de clientes")
public class EquipoController {

    private final EquipoService equipoService;

    @PostMapping
    @Operation(summary = "Crear un equipo")
    public ResponseEntity<EquipoResponseDTO> crear(@RequestBody EquipoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo")
    public ResponseEntity<EquipoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody EquipoRequestDTO dto
    ) {
        return ResponseEntity.ok(equipoService.actualizar(id, dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID")
    public ResponseEntity<EquipoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipoService.obtenerPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los equipos")
    public ResponseEntity<List<EquipoResponseDTO>> listar() {
        return ResponseEntity.ok(equipoService.listar());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar equipos por cliente")
    public ResponseEntity<List<EquipoResponseDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(equipoService.listarPorCliente(clienteId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

